package com.example.economy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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

        mAuth = FirebaseAuth.getInstance();

        cardCartera01 = (CardView) findViewById(R.id.cardCartera_01);
        cardCartera02 = (CardView) findViewById(R.id.cardCartera_02);
        cardCartera03 = (CardView) findViewById(R.id.cardCartera_03);

        cardCartera01.setOnClickListener(this::onClick);
        cardCartera02.setOnClickListener(this::onClick);
        cardCartera03.setOnClickListener(this::onClick);

        //cartera01 = new Carteras(db, 1, 0,0, 0, "cartera1");

    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardCartera_01:
                Log.i("btnCartera1", "Se ejecuto onClick de cartera 01");
                setup();
                Intent intent = new Intent(v.getContext(), NewUserActivity.class);
                startActivity(intent);
                break;
            case R.id.cardCartera_02:
                Log.i("btnCartera2", "Se ejecuto onClick de cartera 02");
                Intent intent1 = new Intent(v.getContext(), NewUserActivity.class);
                startActivity(intent1);
                break;
            case R.id.cardCartera_03:
                Log.i("btnCartera3", "Se ejecuto onClick de cartera 03");
                Intent intent2 = new Intent(v.getContext(), NewUserActivity.class);
                startActivity(intent2);
                break;
            default:break;
        }

    }

    private void setup(){

       DocumentReference dr = db.collection("Users").document("Ignacio.f.diaz98@gmail.com").collection("Carteras").document("Cartera01");
        dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("prueba", "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d("prueba", "No such document");
                    }
                } else {
                    Log.d("prueba", "get failed with ", task.getException());
                }

            }
        });
    }
}