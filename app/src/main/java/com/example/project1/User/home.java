package com.example.project1.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project1.R;
import com.example.project1.admin.admin_login;

public class home extends AppCompatActivity {
    private Button admin,login,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        admin=findViewById(R.id.button4);
        login= findViewById(R.id.button5);
        signup= findViewById(R.id.button6);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myInt=new Intent(home.this, admin_login.class);
                startActivity(myInt);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myInt=new Intent(home.this, user_login.class);
                startActivity(myInt);
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myInt=new Intent(home.this, user_signup.class);
                startActivity(myInt);
            }
        });

    }
}