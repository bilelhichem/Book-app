package com.environment.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class homeact extends AppCompatActivity {
CardView cardhome , cardfav , cardprof , cardsetting , gotoprofil ;
TextView titre ;
ImageView signout ;
FirebaseAuth mauth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeact);
        init();
        titre.setText("Home");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FrameFragment,new homefrag())
                .commit();

        gotoprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FrameFragment,new profilfrag())
                        .commit();
                cardhome.setCardBackgroundColor(getColor(R.color.dirbrq));
                cardfav.setCardBackgroundColor(getColor(R.color.dirbrq));
                cardprof.setCardBackgroundColor(getColor(R.color.red));
                cardsetting.setCardBackgroundColor(getColor(R.color.dirbrq));
                titre.setText("Profil");
            }
        });



        cardhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardhome.setCardBackgroundColor(getColor(R.color.red));
                cardfav.setCardBackgroundColor(getColor(R.color.dirbrq));
                cardprof.setCardBackgroundColor(getColor(R.color.dirbrq));
                cardsetting.setCardBackgroundColor(getColor(R.color.dirbrq));
                titre.setText("Home");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FrameFragment,new homefrag())
                        .commit();
            }
        });


        cardfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardhome.setCardBackgroundColor(getColor(R.color.dirbrq));
                cardfav.setCardBackgroundColor(getColor(R.color.red));
                cardprof.setCardBackgroundColor(getColor(R.color.dirbrq));
                cardsetting.setCardBackgroundColor(getColor(R.color.dirbrq));
                titre.setText("Favorite");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FrameFragment,new favfrag())
                        .commit();
            }
        });


        cardprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardhome.setCardBackgroundColor(getColor(R.color.dirbrq));
                cardfav.setCardBackgroundColor(getColor(R.color.dirbrq));
                cardprof.setCardBackgroundColor(getColor(R.color.red));
                cardsetting.setCardBackgroundColor(getColor(R.color.dirbrq));
                titre.setText("Profil");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FrameFragment,new profilfrag())
                        .commit();
            }
        });


        cardsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardhome.setCardBackgroundColor(getColor(R.color.dirbrq));
                cardfav.setCardBackgroundColor(getColor(R.color.dirbrq));
                cardprof.setCardBackgroundColor(getColor(R.color.dirbrq));
                cardsetting.setCardBackgroundColor(getColor(R.color.red));
                titre.setText("Settings");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FrameFragment,new settingfrag())
                        .commit();
            }
        });


signout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        mauth.signOut();
        Intent intent = new Intent(homeact.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
});



    }

    public void init(){
        cardhome = findViewById(R.id.cardhome);
        cardfav = findViewById(R.id.cardfav);
        cardprof = findViewById(R.id.cardprofil);
        cardsetting = findViewById(R.id.cardsetting);
        titre = findViewById(R.id.titredepage);
        signout = findViewById(R.id.signout);
        mauth = FirebaseAuth.getInstance();
        gotoprofil = findViewById(R.id.gotoprofil);

    }


}