package com.example.demojwt.controlador;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demojwt.modelos.User;
import com.example.demojwt.repositorios.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	@CrossOrigin
	@GetMapping("user")
	public User login(@RequestParam("username") String username, @RequestParam("contrasena") String pwd) {


		User usuario = userRepository.iniciarSesion(username, pwd);
		if(usuario.getUsername().equals(username)){
			String token = getJWTToken(username);
			usuario.setToken(token);		
			
		}
		
		return usuario;
		
	}

	@CrossOrigin
	@GetMapping("/listar-usuarios")
    public List<User> getAllUsuarios(){
        return userRepository.findAll();
    }

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
