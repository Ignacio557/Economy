package com.example.economy.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.economy.CRUD.Carteras;
import com.example.economy.R;

public class Form_CrearCartera extends AppCompatDialogFragment{

    private EditText eTxt_NombreCartera;
    private EditText eTxt_saldoCartera;
    private Carteras newCartera;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        newCartera = new Carteras(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_formulario_cartera, null);
        eTxt_NombreCartera = view.findViewById(R.id.edit_nombreCartera);
        eTxt_saldoCartera = view.findViewById(R.id.edit_saldoCartera);

        builder.setView(view)
                .setTitle("Nueva Cartera")
                .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nombre  = eTxt_NombreCartera.getText().toString();
                        String S_saldo = eTxt_saldoCartera.getText().toString();
                        Float F_saldo = Float.parseFloat(S_saldo);

                        Log.i("insert", "insertamos "+nombre+" con el saldo "+S_saldo);
                        newCartera.insertCartera(nombre, F_saldo);
                    }
                });



        return builder.create();
    };



};
