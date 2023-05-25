package com.example.examen.Clases;

public class Anime {
    String nombres;
    String descripcion;
    String fotos;
    Boolean estrella;

    public Anime(){}

    public Anime(String nombres, String descripcion, String fotos, Boolean estrella) {
        //datos de mi contacto
        this.nombres = nombres;
        this.descripcion = descripcion;
        this.fotos = fotos;
        this.estrella = estrella;
    }

    public String getNombre() {
        return nombres;
    }

    public void setNombre(String nombres) {
        this.nombres = nombres;
    }

    public String getNumero() {
        return descripcion;
    }

    public void setNumero(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return fotos;
    }

    public void setFoto(String fotos) {
        this.fotos = fotos;
    }

    public Boolean getEstrella() {
        return estrella;
    }

    public void setEstrella(Boolean estrella) {
        this.estrella = estrella;
    }
}