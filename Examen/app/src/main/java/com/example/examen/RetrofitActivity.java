package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.examen.Adapters.ContactAdapter;
import com.example.examen.Adapters.UsersAdapter;
import com.example.examen.Clases.Contacto;
import com.example.examen.entities.Users;
import com.example.examen.services.UsersService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        RecyclerView rvListaContactos = findViewById(R.id.rvListaResgistro);
        rvListaContactos.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64781c33362560649a2d370d.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersService servicio = retrofit.create(UsersService.class);
        Call<List<Users>> llamadoLista = servicio.ObtenerContactos();

        llamadoLista.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                List<Users> listaContacto = response.body();
                UsersAdapter adapter = new UsersAdapter(listaContacto);
                rvListaContactos.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

            }
        });

    }
}