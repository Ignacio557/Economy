package com.example.economy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.economy.Dialog.Form_CrearCartera;
import com.example.economy.connection.ConnectionSQLiteHelper;
import com.example.economy.fragment.HomeFragment;
import com.example.economy.fragment.InformesFragment;
import com.example.economy.fragment.MovimientosFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private BottomNavigationView btnMenu;
    private Fragment fragment;
    private FragmentManager manager;

    SQLiteDatabase bd;

     HomeFragment homeFragment = new HomeFragment();
     MovimientosFragment movimientosFragment = new MovimientosFragment();
     InformesFragment informesFragment = new InformesFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        //instanciamos la base de datos
        ConnectionSQLiteHelper conn = new ConnectionSQLiteHelper(HomeActivity.this);
        bd = conn.getWritableDatabase();

        initView();
        initValues();
        initListener();

    }

    //comprueba si el usuario esta loggeado para darle paso a la app
    @Override
    protected void onStart(){
        super.onStart();

        FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
        if(mFirebaseUser != null){

        }else{
            Toast.makeText(HomeActivity.this, "Error de Conexion", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        };
    };

    @Override
    public void onClick(View v){
        switch (v.getId()){

        }
    }

    private void initView(){
        btnMenu = findViewById(R.id.btnMenu);
    }

    private void initValues(){
        manager = getSupportFragmentManager();
        Log.i("Fragment", "cargamos el fragmento: " +fragment);
        LoadFirstFragment();
    }

    //CAMBIA ENTRE LOS FRAGMENTS DEL MENUBAR
    private void initListener(){
        btnMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_Home:
                        fragment = HomeFragment.newInstance();
                        openFragemnt(fragment);
                        return true;
                    case R.id.menu_Informes:
                        fragment = InformesFragment.newInstance();
                        openFragemnt(fragment);
                        return true;
                    case R.id.menu_Movimientos:
                        fragment = MovimientosFragment.newInstance();
                        openFragemnt(fragment);
                        return true;
                }
                return false;
            }
        });
     };

     private void openFragemnt(Fragment fragment){
         Log.i("Fragment", "cargamos el fragmento: " +fragment);
         manager.beginTransaction().replace(R.id.frameContainer, fragment).commit();
    }
    private void LoadFirstFragment(){
         fragment = HomeFragment.newInstance();
         Log.i("Fragment", "cargamos el fragmento: " +fragment);
         openFragemnt(fragment);
    }

    public void logOut(View view){
        mAuth.signOut();
    }

}
