package com.environment.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class profilfrag extends Fragment {
View mview ;
ImageView imagepersone , instlinkgit ;
TextView namepersone ;
FirebaseAuth mauth ;
DatabaseReference datadb ;
CardView changeinfo ;
SharedPreferences myshered ;
SharedPreferences jibmlsheared ;
SharedPreferences.Editor  editor ;
String y ,x;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mview = inflater.inflate(R.layout.fragment_profilfrag, container, false);
        myshered = getActivity().getSharedPreferences("myshered", getContext().MODE_PRIVATE);
        jibmlsheared =getActivity().getSharedPreferences("myshered",0);
        editor = myshered.edit();
        init();

        y = jibmlsheared.getString("imgpr" , null);
        if (y == null) {
            datadb.child("imageprofil")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            editor.putString("imgpr",snapshot.getValue().toString());
                            editor.commit();
                            y = jibmlsheared.getString("imgpr",null);
                            Picasso.get().load(y)
                                    .into(imagepersone);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }else {
            y = jibmlsheared.getString("imgpr",null);
            Picasso.get().load(y)
                    .into(imagepersone);
        }



        x = jibmlsheared.getString("name",null) ;
        if (x== null){
            datadb.child("nameuser").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    editor.putString("name",snapshot.getValue().toString());
                    editor.commit();
                    x = jibmlsheared.getString("name",null);
                    namepersone.setText(x);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else {
            x = jibmlsheared.getString("name",null);
            namepersone.setText(x);
        }






changeinfo.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), com.environment.myapplication.changeinfo.class);
        startActivity(intent);
    }
});



        instlinkgit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), com.environment.myapplication.instalinkgith.class);
                startActivity(intent);
            }
        });






        return  mview ;
    }
    public  void init(){
        changeinfo = mview.findViewById(R.id.changeinfo);
        imagepersone = mview.findViewById(R.id.imgpersone);
        namepersone = mview.findViewById(R.id.namepersone);
        mauth = FirebaseAuth.getInstance();
    datadb = FirebaseDatabase.getInstance(getActivity().getString(R.string.Db_url)).getReference().child("users").child(mauth.getCurrentUser().getUid());
    instlinkgit = mview.findViewById(R.id.insgitlink);

    }
}