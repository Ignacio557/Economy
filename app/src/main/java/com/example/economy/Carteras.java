package com.example.economy;

import com.google.firebase.firestore.FirebaseFirestore;


public class Carteras {

    private FirebaseFirestore db;
    private int ID;
    private float sumatorio, ingresos, gastos;
    private String name, email;

    public Carteras(FirebaseFirestore db, int ID, float sumatorio, String name) {
        this.db = db;
        this.ID = ID;
        this.sumatorio = sumatorio;
        this.ingresos = 0;
        this.gastos = 0;
        this.name = name;
    }

    public Carteras(FirebaseFirestore db, int ID, String email) {
        this.db = db;
        this.ID = ID;
        this.sumatorio = 0;
        this.ingresos = 0;
        this.gastos = 0;
        this.name = "";
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getSumatorio() {
        return sumatorio;
    }

    public void setSumatorio(float sumatorio) {
        this.sumatorio = sumatorio;
    }

    public float getIngresos() {
        return ingresos;
    }

    public void setIngresos(float ingresos) {
        this.ingresos = ingresos;
    }

    public float getGastos() {
        return gastos;
    }

    public void setGastos(float gastos) {
        this.gastos = gastos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}//end class
