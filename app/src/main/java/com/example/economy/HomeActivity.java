package com.example.economy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Carteras cartera01;
    private Carteras cartera02;
    private Carteras cartera03;

    private CardView cardCartera01;
    private CardView cardCartera02;
    private CardView cardCartera03;

    private CardView C1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardCartera01 = (CardView) findViewById(R.id.cardCartera_01);
        cardCartera02 = (CardView) findViewById(R.id.cardCartera_02);
        cardCartera03 = (CardView) findViewById(R.id.cardCartera_03);

        cartera01 = new Carteras(db, 1, 0,0, 0, "cartera1");

    }

    public void onClick(View v){

        switch (v.getId()){
            case R.id.cardCartera_01:
                Log.i("btnCartera1", "Se ejecuto onClick de cartera 01");

                break;
            case R.id.cardCartera_02:
                Log.i("btnCartera2", "Se ejecuto onClick de cartera 01");
                break;
            case R.id.cardCartera_03:
                Log.i("btnCartera3", "Se ejecuto onClick de cartera 01");
                break;
        }

    }

    private void setup(){

        db.collection("Cateras").document("hh").set(cartera01);

    }

}