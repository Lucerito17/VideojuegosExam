package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Button btnRegistroPokemon = findViewById(R.id.btnRegistroPokemon);
        Button btnListaPokemones = findViewById(R.id.btnListaPokemones);

        btnRegistroPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InicioActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        btnListaPokemones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RetrofitActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}