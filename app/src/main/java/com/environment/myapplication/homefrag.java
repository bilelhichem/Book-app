package com.environment.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.environment.myapplication.Adapter.Adapterbook;
import com.environment.myapplication.Model.bookmodel;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class homefrag extends Fragment {
View mview ;
RecyclerView recli ;
DatabaseReference datadb ;
SearchView searchView ;
 TextView category ;

    Adapterbook adapterbook;
    ArrayList<bookmodel> bookmodels ;
    ArrayList<String> dataList ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_homefrag, container, false);
        init();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recli.setLayoutManager(linearLayoutManager);
        learntofirebase();



        // hena rah tsra searche///////////////////////////////////////////////////////////////////////////

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")){
                    learntofirebase();
                }
                else{
                filterDataList(newText);}
                return true;
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),category.class));
            }
        });







        return  mview ;
    }




    public void init(){

        category = mview.findViewById(R.id.category);
  recli = mview.findViewById(R.id.recli);
  searchView = mview.findViewById(R.id.searchview);
  datadb = FirebaseDatabase.getInstance(getActivity().getString(R.string.Db_url)).getReference().child("bookpdf");
    }









    public void learntofirebase(){
        bookmodels = new ArrayList<>();
        dataList = new ArrayList<>();
        datadb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             bookmodels.clear();
             dataList.clear();
             for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                     bookmodel bookmodel = dataSnapshot.getValue(com.environment.myapplication.Model.bookmodel.class);
                     bookmodels.add(bookmodel);
                    dataList.add(dataSnapshot.child("namebook").getValue().toString());
             }
                 adapterbook = new Adapterbook(getActivity(),bookmodels);
             recli.setAdapter(adapterbook);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    private void filterDataList(String query) {
        ArrayList<String> filteredDataList = new ArrayList<>();
        for (String data : dataList) {
            if (data.toLowerCase().contains(query.toLowerCase())) {
                filteredDataList.add(data);
            }
        }
        ArrayList recherch = new ArrayList<>() ;
      datadb.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
                 recherch.clear();
                 for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                     for (String dd : filteredDataList){
                         if (dd.toLowerCase().contains(dataSnapshot.child("namebook").getValue().toString().toLowerCase())) {
                             bookmodel bookmodel = dataSnapshot.getValue(com.environment.myapplication.Model.bookmodel.class);
                             recherch.add(bookmodel);
                         }
                     }
                 }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });

        adapterbook = new Adapterbook(getActivity(),recherch);
        recli.setAdapter(adapterbook);
        // update the adapter with the filtered data
    }


}

