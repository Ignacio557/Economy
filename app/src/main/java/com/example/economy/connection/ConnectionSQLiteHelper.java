package com.example.economy.connection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.economy.Utilities.Utilidades;

public class ConnectionSQLiteHelper extends SQLiteOpenHelper {

    //constructor de la base de datos
    public ConnectionSQLiteHelper(@Nullable Context context) {
        super(context, Utilidades.DATABASE_NOMBRE, null, Utilidades.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.TBL_CTR);
        db.execSQL(Utilidades.TBL_ING);
        db.execSQL(Utilidades.TBL_GST);
    }

    //cuando cambie la version de la bd borrara la rabla antigua y creara la nueva
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLE_CARTERAS);
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLE_INGRESOS);
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLE_GASTOS);
        onCreate(db);
    }
}
