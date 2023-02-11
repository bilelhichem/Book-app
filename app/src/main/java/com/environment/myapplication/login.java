package com.environment.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class login extends AppCompatActivity {
Button register , signin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.register);
        signin  = findViewById(R.id.signin);

 register.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         register.setBackground(getDrawable(R.drawable.back4));
         register.setTextColor(getColor(R.color.black));
         signin.setTextColor(getColor(R.color.white));
         signin.setBackground(getDrawable(R.drawable.back3));
         startActivity(new Intent(login.this,registeract.class));
         finish();

     }
 });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register.setBackground(getDrawable(R.drawable.back3));
                signin.setBackground(getDrawable(R.drawable.back4));
                register.setTextColor(getColor(R.color.white));
                signin.setTextColor(getColor(R.color.black));
                startActivity(new Intent(login.this,signinact.class));
                finish();
            }
        });


    }
}