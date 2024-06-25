package com.example.project1.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class user_signup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText edPassword,edEmail;
    Button btn;
    TextView tv;
    ProgressBar progressBar;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(user_signup.this, MainActivity.class));

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_signup);


        mAuth=FirebaseAuth.getInstance();

        //edUsername = findViewById(R.id.editTextText3);
        edEmail=findViewById(R.id.editTextText7);
        edPassword = findViewById(R.id.editTextText4);
        //conPassword = findViewById(R.id.editTextText5);
        btn = findViewById(R.id.button2);
        tv = findViewById(R.id.textView2);
        progressBar=findViewById(R.id.progress);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                //String confirmPassword = conPassword.getText().toString();
                progressBar.setVisibility(View.VISIBLE);

                if (email.isEmpty() || password.isEmpty() ) {
                    Toast.makeText(getApplicationContext(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                } else if (!RegistrationUtils.isValidEmail(email)) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                }  else if (!RegistrationUtils.isValidPassword(password)) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid password (at least 8 characters, letter, and digit)", Toast.LENGTH_SHORT).show();
                }  else {
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registered Successfully. Please Login", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(user_signup.this, user_login.class));
                            }else{
                                Toast.makeText(getApplicationContext(), "Registered failed", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });



                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(user_signup.this, user_login.class));
            }
        });
    }
}