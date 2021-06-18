package com.example.economy.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
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
import com.example.economy.CRUD.Movimientos;
import com.example.economy.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Form_CrearMovimiento extends AppCompatDialogFragment {

    private EditText eTxt_CarteraEmisora;
    private EditText eTxt_CarteraReceptora;
    private EditText eTxt_TituloMoviminetos;
    private EditText eTxt_Saldo;
    private Movimientos newTransaccion;
    //private CrearCarteraListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        newTransaccion = new Movimientos(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_formulario_movimientos, null);

        builder.setView(view)
                .setTitle("Nueva Transaccion")
                .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Realizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String S_emisor   = eTxt_CarteraEmisora.getText().toString();
                        String S_receptor = eTxt_CarteraReceptora.getText().toString();
                        String titulo   = eTxt_TituloMoviminetos.getText().toString();
                        String S_saldo  = eTxt_Saldo.getText().toString();

                        int emisor = Integer. parseInt(S_emisor);
                        int receptor = Integer. parseInt(S_receptor);
                        Float F_saldo = Float.parseFloat(S_saldo);

                        Log.i("insert", "insertamos movimiento "+ titulo +" con el saldo "+S_saldo);
                        newTransaccion.tranferencia(titulo, emisor, receptor, F_saldo);
                    }
                });

        eTxt_CarteraEmisora = view.findViewById(R.id.edit_CarteraEmisora);
        eTxt_CarteraReceptora = view.findViewById(R.id.edit_CarteraReceptora);
        eTxt_TituloMoviminetos = view.findViewById(R.id.edit_TituloMovimiento);
        eTxt_Saldo = view.findViewById(R.id.edit_saldoCartera);
        eTxt_Saldo.setFilters(new InputFilter[]{new Form_CrearCartera.DecimalDigitsInputFilter(5, 2)});

        return builder.create();
    };

    class DecimalDigitsInputFilter implements InputFilter {
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

}
