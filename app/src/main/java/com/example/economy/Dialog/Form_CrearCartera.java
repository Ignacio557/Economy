package com.example.economy.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.economy.CRUD.Carteras;
import com.example.economy.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        eTxt_saldoCartera.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(5, 2)});
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
                        Float F_saldo  = Float.parseFloat(S_saldo);

                        Log.i("insert", "insertamos "+nombre+" con el saldo "+S_saldo);
                        newCartera.insertCartera(nombre, F_saldo);
                    }
                });



        return builder.create();
    };

    static class DecimalDigitsInputFilter implements InputFilter {
        private Pattern mPattern;
        DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
            mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?");
        }
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher matcher = mPattern.matcher(dest);
            if (!matcher.matches())
                return "";
            return null;
        }
    }

};
