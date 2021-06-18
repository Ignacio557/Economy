package com.example.economy.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.economy.Entities.Cartera;
import com.example.economy.Utilities.Utilidades;
import com.example.economy.connection.ConnectionSQLiteHelper;

import java.lang.reflect.Array;
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

    public void sacarDinero(int idCartera, float saldo) {
        try {
            ConnectionSQLiteHelper conn = new ConnectionSQLiteHelper(context);
            SQLiteDatabase dbUpdate = conn.getWritableDatabase();
            SQLiteDatabase dbRead = conn.getReadableDatabase();
            ContentValues values = new ContentValues();


            String Strg_id[] = {String.valueOf(idCartera)};
            String Strg_saldo = String.valueOf(saldo);

            values.put("Id", idCartera);
            values.put("SaldoTotal", Strg_saldo);

            Log.i("Gasto", "Retiramos. "+saldo+" de la cuenta "+idCartera);

            Cursor cursor =  dbRead.rawQuery("select SaldoTotal from Carteras where Id = ?", Strg_id);
            cursor.moveToFirst();
            float saldoCartera = cursor.getFloat(0);
            saldo = saldoCartera - saldo;

            dbUpdate.execSQL("Update Carteras set SaldoTotal = "+saldo+" where Id="+idCartera);
        }catch (Exception ex){
            ex.toString();
        }

    }

    public void ingresarDinero(int idCartera, float saldo) {
        try {
            ConnectionSQLiteHelper conn = new ConnectionSQLiteHelper(context);
            SQLiteDatabase dbUpdate = conn.getWritableDatabase();
            SQLiteDatabase dbRead = conn.getReadableDatabase();
            ContentValues values = new ContentValues();

            String Strg_id[] = {String.valueOf(idCartera)};
            String Strg_saldo = String.valueOf(saldo);

            values.put("Id", idCartera);
            values.put("SaldoTotal", Strg_saldo);

            Log.i("Ingreso", "ingresamos. "+saldo+" en la cuenta "+idCartera);


            Cursor cursor =  dbRead.rawQuery("select SaldoTotal from Carteras where Id = ?", Strg_id);
            cursor.moveToFirst();
            float saldoCartera = cursor.getFloat(0);
            saldo = saldoCartera + saldo;

            dbUpdate.execSQL("Update Carteras set SaldoTotal = "+saldo+" where Id="+idCartera);
        }catch (Exception ex){
            ex.toString();
        }

    }
};
