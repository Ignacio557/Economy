package com.example.economy.Utilities;

public class Utilidades {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NOMBRE = "Cateras";
    public static final String TABLE_CARTERA = "t_contactos";

    public static final String TBL_GST = "CREATE TABLE Gastos     (Id INTEGER PRIMARY KEY AUTOINCREMENT, IdCartera INTEGER NOT NULL, Titulo VARCHAR2 NOT NULL, Comentario VARCHAR2, hastag TEXT, Fecha DATE, Saldo DECIMAL(10,2), FOREIGN KEY (IdCartera) REFERENCES Carteras(id) )";
    public static final String TBL_ING = "CREATE TABLE Ingresos   (Id INTEGER PRIMARY KEY AUTOINCREMENT, IdCartera INTEGER NOT NULL, Titulo VARCHAR2 NOT NULL, Comentario VARCHAR2, hastag TEXT, Fecha DATE, Saldo DECIMAL(10,2), FOREIGN KEY (IdCartera) REFERENCES Carteras(id) )";
    public static final String TBL_CTR = "CREATE TABLE Carteras   (Id INTEGER PRIMARY KEY AUTOINCREMENT, Titulo VARCHAR2 NOT NULL, Comentario VARCHAR2, SaldoTotal DECIMAL(10,2))";


}
