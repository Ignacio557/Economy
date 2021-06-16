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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.economy.Dialog.Form_CrearTransaccion;
import com.example.economy.Entities.Cartera;
import com.example.economy.Entities.Movimiento;
import com.example.economy.R;
import com.example.economy.connection.ConnectionSQLiteHelper;

import java.util.ArrayList;


public class MovimientosFragment extends Fragment {
    ConnectionSQLiteHelper conn;
    private View vista;

    private CardView btn_CrearCartera;
    private CardView btn_CrearMovimiento;
    private CardView btn_CargarCarteras;

    protected ArrayList<String> inf_carteras;
    protected ArrayList<Cartera>  arr_carteras;
    private Spinner spn_Carteras;

    protected ArrayList<String> infTransacciones;
    protected ArrayList<Movimiento>  listaCartera;
    private ListView listaTransacciones;

    private TextView Txt_SaldoTotal;

    public MovimientosFragment() {
        // Required empty public constructor
    }

    public static MovimientosFragment newInstance() {
        Bundle args = new Bundle();
        
        MovimientosFragment fragment = new MovimientosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_movimientos, container, false);
        FragmentManager manager = this.getParentFragmentManager();

        //Carga las carteras en el desplegable
        spn_Carteras = (Spinner) vista.findViewById(R.id.snp_Carteras);
        consultarCarteras();
        ArrayAdapter<CharSequence> cargarcarteras = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, inf_carteras);
        spn_Carteras.setAdapter(cargarcarteras);

        Txt_SaldoTotal = (TextView) vista.findViewById(R.id.Txt_SaldoTotal);
        Txt_SaldoTotal.setText(getSaldoAbsoluto());

        //Muestra el saldo total de la cartera
        listaTransacciones = (ListView) vista.findViewById(R.id.ListaTransacciones);
        ConsultarMovimientos();

        //Carga  los Movimientos de la cartera en el listvie
        ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_expandable_list_item_1, infTransacciones);
        listaTransacciones.setAdapter(adapter);




        btn_CrearCartera = (CardView) vista.findViewById(R.id.btn_NewIngreso);
        btn_CrearCartera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("boton", "Click en lo boton Crear Cartera");
                Form_CrearTransaccion CrearTransaccion = new Form_CrearTransaccion();
                CrearTransaccion.show(manager, "");
            }
        });



        btn_CargarCarteras = (CardView) vista.findViewById(R.id.btn_CargarMoviminetos);
        btn_CargarCarteras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("boton", "Click en lo boton Cargar Cartera ");
                ConsultarMovimientos();
                ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_expandable_list_item_1, infTransacciones);
                listaTransacciones.setAdapter(adapter);
            }
        });

        conn = new ConnectionSQLiteHelper(this.getContext());
        SQLiteDatabase bd = conn.getWritableDatabase();

        return vista;
    }

    private void consultarCarteras(){
        conn = new ConnectionSQLiteHelper(this.getContext());
        SQLiteDatabase db = conn.getReadableDatabase();

        Cartera cartera = null;
        arr_carteras = new ArrayList<Cartera>();

        Cursor cursor = db.rawQuery("Select Id, Titulo from Carteras", null);

        while (cursor.moveToNext()){
            cartera = new Cartera();
            cartera.setID(cursor.getInt(0));
            cartera.setNombre(cursor.getString(1));
            arr_carteras.add(cartera);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        inf_carteras = new ArrayList<String>();
        inf_carteras.add("Todas");
        for (int i=0; i<inf_carteras.size(); i++){
            inf_carteras.add(arr_carteras.get(i).getID()+" "+arr_carteras.get(i).getNombre());
        }
    }

    public void ConsultarMovimientos() {
        conn = new ConnectionSQLiteHelper(this.getContext());
        SQLiteDatabase db = conn.getReadableDatabase();
        Movimiento movimiento = null;
        listaCartera = new ArrayList<Movimiento>();
        Cursor cursor1 = db.rawQuery("select IdCartera, Titulo, saldo from Ingresos order by Id desc",null);
        Cursor cursor2 = db.rawQuery("select IdCartera, Titulo, saldo from Gastos order by Id desc",null);

        while(cursor1.moveToNext()){
            movimiento = new Movimiento();
            movimiento.setId(cursor1.getInt(0));
            movimiento.setNombre(cursor1.getString(1));
            movimiento.setSaldo(cursor1.getFloat(3));
            movimiento.setTipo("Ingreso");
            listaCartera.add(movimiento);
        }

        while(cursor2.moveToNext()){
            movimiento = new Movimiento();
            movimiento.setId(cursor2.getInt(0));
            movimiento.setNombre(cursor2.getString(1));
            movimiento.setSaldo(cursor2.getFloat(3));
            movimiento.setTipo("Gasto");
            listaCartera.add(movimiento);
        }

        mostrarLista();

    }

    private void mostrarLista() {
        infTransacciones = new ArrayList<String>();
        for (int i = 0; i<listaCartera.size(); i++){
            infTransacciones.add("Id " +listaCartera.get(i).getId()
                    +" - "+ listaCartera.get(i).getNombre()
                    +" - "+ listaCartera.get(i).getSaldo()+"€ tipo:"+listaCartera.get(i).getTipo());
        }
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

}