package com.example.economy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResetPasswdActivity extends AppCompatActivity {

    protected EditText mtxt_email;
    protected Button mBtn_enviar;

    private String email = "";

    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_passwd);

        mBtn_enviar = findViewById(R.id.btnResetPasswd);
        mtxt_email = findViewById(R.id.editTextTextEmailAddress);
        mAuth = FirebaseAuth.getInstance();

        mDialog = new ProgressDialog(this);

        mBtn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = mtxt_email.getText().toString();
                if(!email.isEmpty()){
                    mDialog.setMessage("Espere un segundo");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    resetPsswd();
                }else{
                    Toast.makeText(ResetPasswdActivity.this, "debe ingresar su email", Toast.LENGTH_SHORT).show();
                }
            }


        });

    }

    private void resetPsswd() {
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ResetPasswdActivity.this, "Revise su correo.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ResetPasswdActivity.this, "No se puedo enviar el correo para restablecer su contraseña", Toast.LENGTH_SHORT).show();
                }

                mDialog.dismiss();
            }
        });
    }
}