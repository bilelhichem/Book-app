package com.environment.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mauth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
mauth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mauth.getCurrentUser() != null){
                    startActivity(new Intent(MainActivity.this,homeact.class));
                    finish();
                }
                else {
                startActivity(new Intent(MainActivity.this,login.class));
                finish();}


            }
        },1100);










    }






}