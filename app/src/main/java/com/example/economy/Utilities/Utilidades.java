package com.example.economy.Utilities;

public class Utilidades {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NOMBRE = "Cateras";
    public static final String TABLE_CARTERAS = "Carteras";
    public static final String TABLE_GASTOS = "Gastos";
    public static final String TABLE_INGRESOS = "Ingresos";

    public static final String TBL_GST = "CREATE TABLE "+ TABLE_GASTOS  + " (Id INTEGER PRIMARY KEY AUTOINCREMENT, IdCartera INTEGER NOT NULL, Titulo VARCHAR2 NOT NULL, Comentario VARCHAR2, hastag TEXT, Fecha DATE, Saldo DECIMAL(10,2), FOREIGN KEY (IdCartera) REFERENCES Cartera(id) )";
    public static final String TBL_ING = "CREATE TABLE "+ TABLE_INGRESOS +" (Id INTEGER PRIMARY KEY AUTOINCREMENT, IdCartera INTEGER NOT NULL, Titulo VARCHAR2 NOT NULL, Comentario VARCHAR2, hastag TEXT, Fecha DATE, Saldo DECIMAL(10,2), FOREIGN KEY (IdCartera) REFERENCES Cartera(id) )";
    public static final String TBL_CTR = "CREATE TABLE "+ TABLE_CARTERAS +" (Id INTEGER PRIMARY KEY AUTOINCREMENT, Titulo VARCHAR2 NOT NULL,   Comentario VARCHAR2, SaldoTotal DECIMAL(6,2))";


}
