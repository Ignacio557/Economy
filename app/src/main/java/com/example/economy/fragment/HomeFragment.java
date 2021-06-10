package com.example.economy.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.economy.HomeActivity;
import com.example.economy.R;
import com.example.economy.ResetPasswdActivity;
import com.example.economy.connection.ConnectionSQLiteHelper;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private Button btn_CrearCartera;

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

        btn_CrearCartera = (Button) vista.findViewById(R.id.btn_NewCartera);

        ConnectionSQLiteHelper conn = new ConnectionSQLiteHelper(this.getContext());
        SQLiteDatabase bd = conn.getWritableDatabase();

        return vista;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_NewCartera:
                openDialog();
        }
    }

    private void openDialog() {

    }

    ;

    private void registrarCartera (){
        //instanciamos la base de datos

    }

}