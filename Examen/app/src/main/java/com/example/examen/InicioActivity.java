package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.examen.entities.Users;
import com.example.examen.services.UsersService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        EditText etNombre = findViewById(R.id.etNombreInicio);
        EditText etEmail = findViewById(R.id.etEmailInicio);
        EditText etUsername = findViewById(R.id.etUsernameInicio);
        EditText etFoto = findViewById(R.id.etFotoInicio);

        Button btnActualizar = findViewById(R.id.btnCrear);
        Button btnVerLista = findViewById(R.id.btnVerLista);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64781c33362560649a2d370d.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsersService servicio = retrofit.create(UsersService.class);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users user = new Users();
                user.nombre = etNombre.getText().toString();
                user.email = etEmail.getText().toString();
                user.username = etUsername.getText().toString();
                user.foto = etFoto.getText().toString();

                Call<Void> actualizar = servicio.CrearContactos(user);
                actualizar.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            Log.i("MAIN_APP", "Se  creó");
                            Intent intent = new Intent(v.getContext(), RetrofitActivity.class);
                            v.getContext().startActivity(intent);
                        };
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.i("MAIN_APP", "No sirve");
                    }
                });
                Intent intent = new Intent(v.getContext(), RetrofitActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        btnVerLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RetrofitActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}