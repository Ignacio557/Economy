package com.example.economy.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.economy.Dialog.Form_CrearCartera;
import com.example.economy.Entities.Cartera;
import com.example.economy.HomeActivity;
import com.example.economy.R;
import com.example.economy.ResetPasswdActivity;
import com.example.economy.Utilities.Utilidades;
import com.example.economy.connection.ConnectionSQLiteHelper;

import java.util.ArrayList;
import java.util.Currency;

public class HomeFragment extends Fragment {

    private CardView btn_CrearCartera;
    private CardView btn_CrearMovimiento;
    private View vista;
    private ListView listaCarteras;
    protected ArrayList<String> infCarteras;
    protected ArrayList<Cartera>  listaCartera;

    ConnectionSQLiteHelper conn;

    public HomeFragment() {
        // Required empty public constructor
    }

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

        listaCarteras = (ListView) vista.findViewById(R.id.ListaCarteras);
        ConsultarCarteras();

        ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_expandable_list_item_1, infCarteras);
        listaCarteras.setAdapter(adapter);

        btn_CrearCartera = (CardView) vista.findViewById(R.id.btn_NewCartera);
        btn_CrearCartera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Form_CrearCartera crearCartera = new Form_CrearCartera();
                crearCartera.show(manager, "");
            }
        });

        conn = new ConnectionSQLiteHelper(this.getContext());
        SQLiteDatabase bd = conn.getWritableDatabase();

        return vista;
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
            cartera.setSaldoTotal(cursor.getInt(2));
            listaCartera.add(cartera);
        }

        mostrarLista();

    }

    private void mostrarLista() {
        infCarteras = new ArrayList<String>();
        for (int i = 0; i<listaCartera.size(); i++){
            infCarteras.add("Id " +listaCartera.get(i).getID()
                +" - "+ listaCartera.get(i).getNombre()
                    +" - "+ listaCartera.get(i).getSaldoTotal());
        }
    }

}