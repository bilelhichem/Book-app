package com.environment.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.environment.myapplication.Adapter.adapfavorite;
import com.environment.myapplication.Model.bookmodel;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class favfrag extends Fragment {
    RecyclerView favlist ;
    View mview ;
    String bookid ;
    DatabaseReference datadb ;
    ArrayList<bookmodel> bookmodels ;
    FirebaseAuth mauth ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mview =  inflater.inflate(R.layout.fragment_favfrag, container, false);
        Init();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        favlist.setLayoutManager(layoutManager);
        initFav();


        return  mview ;
    }



    public  void Init(){
         favlist = mview.findViewById(R.id.favlist);
        mauth = FirebaseAuth.getInstance();
         datadb = FirebaseDatabase.getInstance(getContext().getString(R.string.Db_url)).getReference().child("Favorite").child(mauth.getCurrentUser().getUid());

    }




    private void initFav() {
        bookmodels = new ArrayList<>();
        datadb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            bookmodels.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    bookmodel bookmodel = dataSnapshot.getValue(com.environment.myapplication.Model.bookmodel.class);
                bookmodels.add(bookmodel);

            }
                adapfavorite adapfavorite = new adapfavorite(getActivity(),bookmodels);
              favlist.setAdapter(adapfavorite);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}