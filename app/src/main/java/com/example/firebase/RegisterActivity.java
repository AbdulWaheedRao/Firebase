package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
private EditText etEmail;
private EditText etPassword;
private Button btnRegister;
private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        btnRegister=findViewById(R.id.btnRegister);
        mauth=FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email=etEmail.getText().toString();
                String txt_password=etPassword.getText().toString();

                if (TextUtils.isEmpty(txt_email) ||  TextUtils.isEmpty(txt_password)){

                    Toast.makeText(RegisterActivity.this, "Empty", Toast.LENGTH_SHORT).show();
            }else if (txt_password.length() <= 6){
                    Toast.makeText(RegisterActivity.this, "Password to short", Toast.LENGTH_SHORT).show();
                }else{
                  registerUser(txt_email , txt_password);
                }
        }

            private void registerUser(String email, String password) {

                mauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(RegisterActivity.this, "Registration Cancel", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            });
    }
}