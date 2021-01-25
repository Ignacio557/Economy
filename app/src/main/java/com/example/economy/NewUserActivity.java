package com.example.economy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewUserActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button mBtnSingUp;

    private EditText mTxtEmail;
    private EditText mTxtPsswd;

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        //instanciamos el autentificador de firebase
        mAuth = FirebaseAuth.getInstance();

        mTxtEmail = findViewById(R.id.Txt_Email);
        mTxtPsswd = (EditText) findViewById(R.id.Txt_Psswd);
        mBtnSingUp = (Button) findViewById(R.id.Btn_SignUp);

        mBtnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateUser();

            }
        });



    }//end onCreate

    @Override
    public void onStart() {
        super.onStart();

    }

    private void CreateUser(){

        email = mTxtEmail.getText().toString();
        password = mTxtPsswd.getText().toString();

        if(TextUtils.isEmpty(email)){
            mTxtPsswd.setError("introducca su Email");
            return;
        }else if(TextUtils.isEmpty(password)){
            mTxtPsswd.setError("introducca su contraseña");
            return;
        }else if(password.length() < 8){
            Toast.makeText(NewUserActivity.this, "La contraseña debe tener minimo 8 Digitos",
                    Toast.LENGTH_SHORT).show();
            return;
        }else if(isValidEmail(email) != true){
            mTxtEmail.setError("Email no valido");
            return;
        }

        Log.i("Credenciales", email + ", " + password);


        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("createUser", "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent intent = new Intent(NewUserActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("createUser", "createUserWithEmail:failure");
                    Toast.makeText(NewUserActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }
            }
        });

    }//end of CreateUser()

    /*
    * Metod: comprueba si el email tiene in formato correcto
    * return: devuelve un True si esta bien y False si esta mal
    * */
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}//end class