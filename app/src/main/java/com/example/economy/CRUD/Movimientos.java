package com.example.economy.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.economy.Utilities.Utilidades;
import com.example.economy.connection.ConnectionSQLiteHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Movimientos extends ConnectionSQLiteHelper {
    Context context;

    private Carteras crud_cartera = new Carteras(context);

    long ahora = System.currentTimeMillis();
    Date fecha = new Date(ahora);
    DateFormat df = new SimpleDateFormat("dd/MM/yy");
    String FechaActual = df.format(fecha);

    public Movimientos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long tranferencia(String titulo, int Emisor, int Receptor, float saldoTotal){
        long id = 0;
        try {
            ConnectionSQLiteHelper conn = new ConnectionSQLiteHelper(context);
            SQLiteDatabase db = conn.getWritableDatabase();

            ContentValues valuesEmision = new ContentValues();
            ContentValues valuesRecepccion = new ContentValues();

            String Strg_idE[] = {String.valueOf(Emisor)};
            String Strg_idR[] = {String.valueOf(Receptor)};

            Cursor idE = db.rawQuery("SELECT Id FROM Carteras Where Id=?", Strg_idE);
            Cursor idR = db.rawQuery("SELECT Id FROM Carteras Where Id=?", Strg_idR);

            int IdEmisor = idE.getInt(0);
            int IdReceptor = idR.getInt(0);

            //valores a insertar en la tabla de gastos de la carteera que emite la transferencia
            valuesEmision.put("Titulo", titulo);
            valuesEmision.put("Saldo", saldoTotal);
            valuesEmision.put("IdCartera", IdEmisor);
            valuesEmision.put("hastag", "Transferencia");
            valuesEmision.put("Fecha", FechaActual);

            //valores a insertar en la tabla de gastos de la carteera que emite la transferencia
            valuesEmision.put("Titulo", titulo);
            valuesEmision.put("SaldoTotal", saldoTotal);
            valuesEmision.put("IdCartera", IdReceptor);
            valuesEmision.put("hastag", "Transferencia");
            valuesEmision.put("Fecha", FechaActual);

            crud_cartera.sacarDinero(IdEmisor, saldoTotal);
            crud_cartera.ingresarDinero(IdReceptor, saldoTotal);

            Log.i("insert", "movimiento bancario entre "+IdEmisor +" y "+IdReceptor+" con un total de "+saldoTotal+"€");
            id = db.insert(Utilidades.TABLE_GASTOS, null, valuesEmision);
            id = db.insert(Utilidades.TABLE_INGRESOS, null, valuesRecepccion);

        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public void Ingreso(int id, float sald){
        crud_cartera.ingresarDinero(id, sald);
    }

    public void Egreso(int id, float sald){
        crud_cartera.sacarDinero(id, sald);
    }

}
