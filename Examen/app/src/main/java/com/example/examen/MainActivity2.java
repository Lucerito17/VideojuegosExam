package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    float num1;
    float num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EditText txtnum1 = findViewById(R.id.txtnum1);
        EditText txtnum2 = findViewById(R.id.txtnum2);

        Button btnsum = findViewById(R.id.btnsuma);
        Button btnresta = findViewById(R.id.btnresta);
        Button btnmulti = findViewById(R.id.btnmulti);
        Button btndiv = findViewById(R.id.btndiv);

        TextView txtrespuesta = findViewById(R.id.txtrespuesta);

        btnsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Float.parseFloat(txtnum1.getText().toString());
                num2 = Float.parseFloat(txtnum2.getText().toString());
                float resulta = num1 + num2;
                txtrespuesta.setText(String.valueOf(resulta));
            }
        });

        btnresta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Float.parseFloat(txtnum1.getText().toString());
                num2 = Float.parseFloat(txtnum2.getText().toString());
                float resulta = num1 - num2;

                txtrespuesta.setText(String.valueOf(resulta));
            }
        });

        btnmulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Float.parseFloat(txtnum1.getText().toString());
                num2 = Float.parseFloat(txtnum2.getText().toString());
                float resulta = num1 * num2;

                txtrespuesta.setText(String.valueOf(resulta));
            }
        });

        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Float.parseFloat(txtnum1.getText().toString());
                num2 = Float.parseFloat(txtnum2.getText().toString());
                float resulta = num1 / num2;

                txtrespuesta.setText(String.valueOf(resulta));
            }
        });
    }
}