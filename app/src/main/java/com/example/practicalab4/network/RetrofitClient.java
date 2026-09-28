package com.example.practicalab4.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Configura Retrofit y proporciona el servicio
 * utilizado para consultar la API.
 */
public final class RetrofitClient {

    // Dirección principal del servicio externo
    private static final String BASE_URL =
            "https://jsonplaceholder.typicode.com/";

    // Servicio compartido por toda la aplicación
    private static UsuarioService usuarioService;

    /**
     * Constructor privado para impedir que se creen
     * objetos RetrofitClient utilizando new.
     */
    private RetrofitClient() {
    }

    /**
     * Devuelve una única instancia de UsuarioService.
     */
    public static synchronized UsuarioService getService() {

        // Solamente crea Retrofit la primera vez
        if (usuarioService == null) {

            Retrofit retrofit = new Retrofit.Builder()

                    // Establece la dirección principal
                    .baseUrl(BASE_URL)

                    // Convierte automáticamente JSON a objetos Java
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    )

                    // Termina la configuración
                    .build();

            // Retrofit implementa UsuarioService automáticamente
            usuarioService = retrofit.create(
                    UsuarioService.class
            );
        }

        return usuarioService;
    }
}