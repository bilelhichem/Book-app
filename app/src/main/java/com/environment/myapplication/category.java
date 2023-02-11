package com.environment.myapplication;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.environment.myapplication.Adapter.adapcat;
import com.environment.myapplication.Model.bookmodel;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class category extends AppCompatActivity {
CardView backcat ;
DatabaseReference datadb ;
RecyclerView catlist ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        init();
        learntodatadb();
        backcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction  = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.maincont,new homefrag());
                finish();
            }
        });





    }



    public void init(){
        backcat = findViewById(R.id.backcat);
        datadb = FirebaseDatabase.getInstance(getString(R.string.Db_url)).getReference().child("bookpdf");
   catlist = findViewById(R.id.catlist);
    }







public void learntodatadb(){
    Boolean boolea = false ;
    ArrayList<bookmodel> bookmodels = new ArrayList<>();
    ArrayList<String> arrayList = new ArrayList<>();
    datadb.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
       bookmodels.clear();
       arrayList.clear();
       for (DataSnapshot dataSnapshot : snapshot.getChildren()){
           if (arrayList.contains(dataSnapshot.child("category").getValue().toString().toLowerCase()) ) {

           } else {
               bookmodel bookmodel = dataSnapshot.getValue(com.environment.myapplication.Model.bookmodel.class);
               bookmodels.add(bookmodel);
               arrayList.add(dataSnapshot.child("category").getValue().toString().toLowerCase());
           }
       }
            adapcat adapterbook = new adapcat(category.this,bookmodels);
            catlist.setAdapter(adapterbook);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(category.this);
            catlist.setLayoutManager(layoutManager);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });


}





}