package com.example.economy.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.economy.connection.ConnectionSQLiteHelper;

public class Carteras extends ConnectionSQLiteHelper {

    Context context;

    public Carteras(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertCartera(String titulo, String comentario, float saldoTotal){
        long id = 0;
        try {
            ConnectionSQLiteHelper conn = new ConnectionSQLiteHelper(context);
            SQLiteDatabase db = conn.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Titulo", titulo);
            values.put("Comentario", comentario);
            values.put("SaldoTotal", saldoTotal);

            id = db.insert("t_contactos", null, values);

        }catch (Exception ex){
            ex.toString();
        }

        return id;

    }

};
