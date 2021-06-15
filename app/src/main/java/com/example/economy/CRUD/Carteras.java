package com.example.economy.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.economy.Entities.Cartera;
import com.example.economy.Utilities.Utilidades;
import com.example.economy.connection.ConnectionSQLiteHelper;

import java.util.ArrayList;

public class Carteras extends ConnectionSQLiteHelper {

    Context context;



    public Carteras(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertCartera(String titulo, float saldoTotal){
        long id = 0;
        try {
            ConnectionSQLiteHelper conn = new ConnectionSQLiteHelper(context);
            SQLiteDatabase db = conn.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Titulo", titulo);
            values.put("Comentario", "");
            values.put("SaldoTotal", saldoTotal);

            Log.i("insert", "inserto los datos de la nueva cartera: nombre "+titulo+" saldo: "+saldoTotal);
            id = db.insert(Utilidades.TABLE_CARTERAS, null, values);
            Log.i("insert", values.toString());
        }catch (Exception ex){
            ex.toString();
        }

        return id;

    }

};
