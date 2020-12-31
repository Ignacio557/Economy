package com.example.economy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;

    private Button mBtnlogin;
    private Button mBtnSignUp;

    private EditText mTxtEmail;
    private EditText mTxtPsswd;

    //variables que vamos a usar para el registro
    private String email = "";
    private String psswd = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtiene la instancia de FirebaseAnalytics.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //Instanciamos firebaseAuth
        mAuth = FirebaseAuth.getInstance();

        mBtnlogin = (Button) findViewById(R.id.Btn_LogIn);
        mBtnSignUp = (Button) findViewById(R.id.Btn_NewUser);
        mTxtEmail = (EditText) findViewById(R.id.Txt_Email);
        mTxtPsswd = (EditText) findViewById(R.id.Txt_Psswd);



        mBtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = mTxtEmail.getText().toString();
                psswd = mTxtPsswd.getText().toString();

                if(!email.isEmpty() && !psswd.isEmpty()){
                    Login();
                }else {

                }

            }
        });

        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), NewUserActivity.class);
                startActivity(intent);
            }
        });

    }

    /*
    *
    *Valdacion e Inicio de sesion
    **/
    private void Login(){

        mAuth.signInWithEmailAndPassword(email, psswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    finish();
                }

            }
        });

    }

}