package com.example.economy.Entities;

public class Movimiento {

    private int Id;
    private float Saldo;
    private String Nombre, Tipo;

    public Movimiento(){

    }

    public Movimiento(int id, float saldo, String nombre, String tipo) {
        Id = id;
        Saldo = saldo;
        Nombre = nombre;
        Tipo = tipo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public float getSaldo() {
        return Saldo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public void setSaldo(float saldo) {
        Saldo = saldo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
