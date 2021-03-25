package com.example.demojwt.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long userId;
    @Column(name = "username",nullable = false)
    private String username;
    @Column(name = "contrasena",nullable = false)
    private String contrasena;
    @Column(name = "nombre_completo",nullable = false)
    private String nombreCompleto;
    private String token;

    public User() {
    }

    public User(String username, String contrasena, String token) {
        this.username = username;
        this.contrasena = contrasena;
        this.token = token;
    }

    public User(String username, String contrasena) {
        this.username = username;
        this.contrasena = contrasena;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
