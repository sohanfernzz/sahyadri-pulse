package com.example.project1.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

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

public class user_login extends AppCompatActivity {
    EditText edEmail,edPassword;
    Button btn;
    TextView tv;
    FirebaseAuth  mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        mAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progress);

        edEmail=findViewById(R.id.editTextText);
        edPassword=findViewById(R.id.editTextText2);

        btn=findViewById(R.id.button);
        tv=findViewById(R.id.textView2);

    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String email=edEmail.getText().toString();
            String password=edPassword.getText().toString();

            if(TextUtils.isEmpty(email)){
                Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), " Login successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(user_login.this, MainActivity.class));

                            } else {

                                Toast.makeText(user_login.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    });
    tv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(user_login.this, user_signup.class));

        }
    });
    }
}