package com.example.economy.Entities;

import com.google.firebase.firestore.FirebaseFirestore;


public class Cartera {

    private int ID;
    private float SaldoTotal;
    private String Nombre, Comentario;

    public Cartera (){

    }

    public Cartera(int ID, float saldoTotal, String nombre, String comentario) {
        this.ID = ID;
        SaldoTotal = saldoTotal;
        Nombre = nombre;
        Comentario = comentario;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getSaldoTotal() {
        return SaldoTotal;
    }

    public void setSaldoTotal(float saldoTotal) {
        SaldoTotal = saldoTotal;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }
}//end class
