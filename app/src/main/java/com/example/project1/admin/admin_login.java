package com.example.project1.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class admin_login extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText edUsername,edPassword;
        Button btn;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

        edUsername=findViewById(R.id.editTextText9);
        edPassword=findViewById(R.id.editTextText10);
        btn=findViewById(R.id.button3);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=edUsername.getText().toString();
                String password=edPassword.getText().toString();
                if(email.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill the details properly",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();
                    Intent myInt=new Intent(admin_login.this, admin_home.class);
                    startActivity(myInt);
                }
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                               // progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), " Login successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(admin_login.this, admin_home.class));

                                } else {

                                    Toast.makeText(admin_login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });}

        });
    }
}