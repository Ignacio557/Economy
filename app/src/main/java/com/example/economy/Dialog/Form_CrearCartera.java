package com.example.economy.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.economy.R;

public class Form_CrearCartera extends AppCompatDialogFragment{

    private EditText eTxt_NombreCartera;
    private EditText eTxt_saldoCartera;
    //private CrearCarteraListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_formulario_cartera, null);


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
                        String saldo = eTxt_saldoCartera.getText().toString();
                        //listener.applyTexts(nombre, saldo);
                    }
                });

        eTxt_NombreCartera = view.findViewById(R.id.edit_nombreCartera);
        eTxt_saldoCartera = view.findViewById(R.id.edit_saldoCartera);

        return builder.create();
    };
/**
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (CrearCarteraListener)  context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " error en el listener del dialog crear cartera");
        }
    }


    public interface CrearCarteraListener{
        void applyTexts(String nombre, String saldo);
    }
*/
};
