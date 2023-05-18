package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> listaEnteros = new ArrayList<Integer>();
    int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnuno = findViewById(R.id.btnuno);
        Button btndos = findViewById(R.id.btndos);
        Button btntres = findViewById(R.id.btntres);
        Button btncalcular = findViewById(R.id.btncalcular);

        TextView txtoperacion = findViewById(R.id.txtoper);
        TextView txtresultado = findViewById(R.id.txtresult);

        btnuno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = txtoperacion.getText().toString();
                listaEnteros.add(1);
                txtoperacion.setText(texto + "+1");
            }
        });

        btndos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = txtoperacion.getText().toString();
                listaEnteros.add(2);
                txtoperacion.setText(texto + "+2");
            }
        });

        btntres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = txtoperacion.getText().toString();
                listaEnteros.add(3);
                txtoperacion.setText(texto + "+3");
            }
        });

        btncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = listaEnteros.stream().reduce(0, Integer::sum);
                txtresultado.setText(String.valueOf(result));
            }
        });
    }
}