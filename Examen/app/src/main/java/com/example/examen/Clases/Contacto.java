package com.example.examen.Clases;

import android.os.Parcelable;

public class Contacto {
    String nombre;
    String numero;
    String foto;

    public Contacto(){}

    public Contacto(String nombre, String numero, String foto) {
        //datos de mi contacto
        this.nombre = nombre;
        this.numero = numero;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
