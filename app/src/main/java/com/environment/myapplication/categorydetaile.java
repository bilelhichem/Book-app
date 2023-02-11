package com.environment.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.environment.myapplication.Adapter.detailecatadap;
import com.environment.myapplication.Model.bookmodel;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class categorydetaile extends AppCompatActivity {
CardView back ;
    String bilel ;
    RecyclerView catli ;
    ArrayList<bookmodel> bookmodels ;
    DatabaseReference datadb ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorydetaile);
        back = findViewById(R.id.backct);
        catli = findViewById(R.id.catli);
        bilel = getIntent().getStringExtra("category");
        datadb = FirebaseDatabase.getInstance(getString(R.string.Db_url)).getReference().child("bookpdf");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(categorydetaile.this);
        catli.setLayoutManager(layoutManager);
        initFav();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(categorydetaile.this,category.class));
                finish();
            }
        });


    }


        private void initFav() {
        bookmodels = new ArrayList<>() ;
        datadb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            bookmodels.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                if (dataSnapshot.child("category").getValue().equals(bilel)){
                    bookmodel bookmodel = dataSnapshot.getValue(com.environment.myapplication.Model.bookmodel.class);
                    bookmodels.add(bookmodel);
                }
            }
                detailecatadap detailecatadap = new detailecatadap(categorydetaile.this,bookmodels);
            catli.setAdapter(detailecatadap);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}