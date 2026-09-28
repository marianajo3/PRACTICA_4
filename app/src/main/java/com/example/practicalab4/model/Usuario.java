package com.example.practicalab4.model;

/**
 * Modelo que representa a un usuario recibido
 * desde la API en formato JSON.
 */
public class Usuario {

    // Datos que llegan desde JSONPlaceholder
    private int id;
    private String name;
    private String username;
    private String email;

    // Devuelve el ID
    public int getId() {
        return id;
    }

    // Asigna el ID
    public void setId(int id) {
        this.id = id;
    }

    // Devuelve el nombre completo
    public String getName() {
        return name;
    }

    // Asigna el nombre completo
    public void setName(String name) {
        this.name = name;
    }

    // Devuelve el nombre de usuario
    public String getUsername() {
        return username;
    }

    // Asigna el nombre de usuario
    public void setUsername(String username) {
        this.username = username;
    }

    // Devuelve el correo
    public String getEmail() {
        return email;
    }

    // Asigna el correo
    public void setEmail(String email) {
        this.email = email;
    }
}