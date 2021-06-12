package com.example.economy.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.economy.Dialog.Form_CrearCartera;
import com.example.economy.HomeActivity;
import com.example.economy.R;
import com.example.economy.ResetPasswdActivity;
import com.example.economy.connection.ConnectionSQLiteHelper;

public class HomeFragment extends Fragment /*implements Form_CrearCartera.CrearCarteraListener*/{

    private CardView btn_CrearCartera;

    private View vista;

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
        btn_CrearCartera = (CardView) vista.findViewById(R.id.btn_NewCartera);
        btn_CrearCartera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Form_CrearCartera crearCartera = new Form_CrearCartera();
                crearCartera.show(manager, "");
            }
        });

        ConnectionSQLiteHelper conn = new ConnectionSQLiteHelper(this.getContext());
        SQLiteDatabase bd = conn.getWritableDatabase();

        return vista;
    }

    public void openDialog (){
        Log.i("Dialog", "onClick en crear cartera");



    }
/*
    //aqui guardamos la informacion del dialog y la usamos para hacer los inserts
    @Override
    public void applyTexts(String nombre, String saldo) {
        Log.i("info", "nombre: "+nombre+" Saldo:"+saldo);
    }
*/
    private void registrarCartera (){
        //instanciamos la base de datos

    }

}