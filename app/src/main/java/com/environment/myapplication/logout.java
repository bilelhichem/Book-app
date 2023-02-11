package com.environment.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class logout extends AppCompatActivity {
ImageView logbara ;
CardView logbak ;
FirebaseAuth mauth ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
      logbara = findViewById(R.id.logbara);
      logbak = findViewById(R.id.logback);
      mauth = FirebaseAuth.getInstance();

        logbak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction  = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.bilelhichem,new settingfrag());
                finish();

            }
        });




      logbara.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              mauth.signOut();
              Intent intent = new Intent(logout.this,MainActivity.class);
              startActivity(intent);
              finish();
          }
      });

    }
}