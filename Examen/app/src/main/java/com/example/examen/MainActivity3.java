package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity3 extends AppCompatActivity {

    int aleatorio1, aleatorio2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button btnjugador = findViewById(R.id.btnjugador);
        Button btnreset = findViewById(R.id.btnreset);

        TextView txtaleatorio1 = findViewById(R.id.txtaleatorio1);
        TextView txtaleatorio2 = findViewById(R.id.txtaleatorio2);
        TextView txtganador = findViewById(R.id.txtganador);

        Random rand = new Random();

        btnjugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtaleatorio1.getText().equals(""))
                {
                    aleatorio1 = rand.nextInt(10)+1;
                    txtaleatorio1.setText(String.valueOf(aleatorio1));
                    btnjugador.setText(String.valueOf("Jugador 2"));
                }
                else {
                    aleatorio2 = rand.nextInt(10)+1;
                    txtaleatorio2.setText(String.valueOf(aleatorio2));
                    if(aleatorio1>aleatorio2)
                    {
                        txtganador.setText("Ganador: Jugador 1");
                    } else if (aleatorio2==aleatorio1) {
                        txtganador.setText("Empate");
                    } else{
                        txtganador.setText("Ganador: Jugador 2");
                    }
                }
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtaleatorio2.setText("");
                txtaleatorio1.setText("");
                txtganador.setText("");
                btnjugador.setText("Jugador 1");
            }
        });
    }
}