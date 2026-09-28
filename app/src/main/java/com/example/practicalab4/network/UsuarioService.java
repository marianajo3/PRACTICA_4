package com.example.practicalab4.network;

import com.example.practicalab4.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Define las solicitudes disponibles para la API.
 */
public interface UsuarioService {

    /**
     * Realiza una solicitud GET a la ruta /users
     * y espera recibir una lista de usuarios.
     */
    @GET("users")
    Call<List<Usuario>> obtenerUsuarios();
}