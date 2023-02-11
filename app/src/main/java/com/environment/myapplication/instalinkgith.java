package com.environment.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;

public class instalinkgith extends AppCompatActivity {
ImageView bilel  , instagram , linkedln , github ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instalinkgith);
init();
        bilel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction  = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.cf,new profilfrag());
                finish();
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://www.instagram.com/bilel_hichem/?hl=fr");
            }
        });

linkedln.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        gotourl("https://www.linkedin.com/in/lakhmi-hichem-billal-a3b273255/");
    }
});


github.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        gotourl("https://github.com/bilelhichem");
    }
});



    }



    public void init(){
        bilel = findViewById(R.id.bilel);
        instagram = findViewById(R.id.insta);
        linkedln = findViewById(R.id.linke);
        github = findViewById(R.id.github);
    }


    public void gotourl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

}