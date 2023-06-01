package com.example.examen.services;

import com.example.examen.entities.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsersService {
    @GET("Contactos")
    Call<List<Users>> ObtenerContactos();

    @PUT("Contactos/{id}")
    Call<Users> EditarContactos(@Path("id") int id,@Body Users user);

    @DELETE("Contactos/{id}")
    Call<Void> EliminarContactos(@Path("id")int id);

    @POST("Contactos")
    Call<Void> CrearContactos(@Body Users user);

    @GET("Contactos/{id}")
    Call<Users> EncontrarContacto(@Path("id") int id);
}
