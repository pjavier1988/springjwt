package com.example.demojwt.repositorios;

import com.example.demojwt.modelos.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository  extends JpaRepository<User,Long>{
    
    @Query(value="Select u from User u where u.username= :username and u.contrasena=:contrasena")
    User iniciarSesion(@Param("username") String username, @Param("contrasena") String contrasena);
}
