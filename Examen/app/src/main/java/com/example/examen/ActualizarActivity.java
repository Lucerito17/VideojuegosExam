package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class ActualizarActivity extends AppCompatActivity {
    private Users user = new Users();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        //EditText etNombreEditar = findViewById(R.id.etNombreEditar);
        //EditText etEmailEditar = findViewById(R.id.etEmailEditar);
        //EditText etUsernameEditar = findViewById(R.id.etUsernameEditar);

        Button btnAtrasActualizar = findViewById(R.id.btnAtrasActualizar);

        Intent intent = getIntent();
        int temp = intent.getIntExtra("identificador", 0);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64781c33362560649a2d370d.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersService servicio = retrofit.create(UsersService.class);
        Call<Users> llamado = servicio.EncontrarContacto(temp);

        btnAtrasActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //user.nombre = etNombreEditar.getText().toString();
                //user.email = etEmailEditar.getText().toString();
                //user.username = etUsernameEditar.getText().toString();

                Call<Users> actualizar = servicio.EditarContactos(temp, user);
                actualizar.enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {
                        Intent intent = new Intent(v.getContext(), RetrofitActivity.class);
                        v.getContext().startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {
                    }
                });
            }
        });
    }
}