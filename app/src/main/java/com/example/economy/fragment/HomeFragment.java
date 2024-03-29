package com.example.economy.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.economy.Dialog.Form_CrearCartera;
import com.example.economy.Dialog.Form_CrearMovimiento;
import com.example.economy.Entities.Cartera;
import com.example.economy.R;
import com.example.economy.connection.ConnectionSQLiteHelper;

import java.util.ArrayList;
import java.util.Currency;

public class HomeFragment extends Fragment {

    private CardView btn_CrearCartera;
    private CardView btn_CrearMovimiento;
    private CardView btn_CargarCarteras;

    protected ArrayList<String> infCarteras;
    protected ArrayList<Cartera>  listaCartera;

    private View vista;
    private ListView listaCarteras;

    private TextView Txt_SaldoTotal;

    ConnectionSQLiteHelper conn;

    public HomeFragment() {}

    public static HomeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_home, container, false);
        FragmentManager manager = this.getParentFragmentManager();

        Txt_SaldoTotal = (TextView) vista.findViewById(R.id.Txt_SaldoTotal);
        Txt_SaldoTotal.setText(getSaldoAbsoluto());

        listaCarteras = (ListView) vista.findViewById(R.id.ListaCarteras);
        ConsultarCarteras();

        ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_expandable_list_item_1, infCarteras);
        listaCarteras.setAdapter(adapter);


        btn_CrearCartera = (CardView) vista.findViewById(R.id.btn_NewCartera);
        btn_CrearCartera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("boton", "Click en lo boton Crear Cartera");
                Form_CrearCartera crearCartera = new Form_CrearCartera();
                crearCartera.show(manager, "");
            }
        });

        btn_CrearMovimiento = (CardView) vista.findViewById(R.id.btn_NewMovimiento);
        btn_CrearMovimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("boton", "Click en lo boton Crear Movimiento");
                Form_CrearMovimiento crearMovimiento = new Form_CrearMovimiento();
                crearMovimiento.show(manager, "");
            }
        });

        btn_CargarCarteras = (CardView) vista.findViewById(R.id.btn_CargarCarteras);
        btn_CargarCarteras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("boton", "Click en lo boton Cargar Cartera ");
                ConsultarCarteras();
                ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_expandable_list_item_1, infCarteras);
                listaCarteras.setAdapter(adapter);
                Txt_SaldoTotal.setText(getSaldoAbsoluto());
            }
        });

        conn = new ConnectionSQLiteHelper(this.getContext());
        SQLiteDatabase bd = conn.getWritableDatabase();

        return vista;
    }

    private String getSaldoAbsoluto() {
        conn = new ConnectionSQLiteHelper(this.getContext());
        SQLiteDatabase db = conn.getWritableDatabase();
        Cursor cursor = db.rawQuery("select sum(SaldoTotal) from Carteras", null);
        cursor.moveToFirst();
        float saldo = cursor.getInt(0);
        String Strg_saldo = String.valueOf(saldo);
        return Strg_saldo;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void ConsultarCarteras() {
        conn = new ConnectionSQLiteHelper(this.getContext());
        SQLiteDatabase db = conn.getReadableDatabase();
        Cartera cartera = null;
        listaCartera = new ArrayList<Cartera>();
        Cursor cursor = db.rawQuery("select * from Carteras",null);

        while(cursor.moveToNext()){
            cartera = new Cartera();
            cartera.setID(cursor.getInt(0));
            cartera.setNombre(cursor.getString(1));
            cartera.setSaldoTotal(cursor.getFloat(3));
            listaCartera.add(cartera);
        }

        mostrarLista();

    }

    private void mostrarLista() {
        infCarteras = new ArrayList<String>();
        for (int i = 0; i<listaCartera.size(); i++){
            infCarteras.add("Id " +listaCartera.get(i).getID()
                +" - "+ listaCartera.get(i).getNombre()
                    +" - "+ listaCartera.get(i).getSaldoTotal()+"€");
        }
    }

}