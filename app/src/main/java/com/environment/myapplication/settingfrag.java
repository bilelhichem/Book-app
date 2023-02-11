package com.environment.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class settingfrag extends Fragment {
    View mview ;
    ImageView changepass  ;
    CardView cardview10;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      mview =  inflater.inflate(R.layout.fragment_settingfrag, container, false);
        changepass = mview.findViewById(R.id.changepass);
        cardview10 = mview.findViewById(R.id.cardView10);


        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),changepassword.class);
                startActivity(intent);

            }
        });
        cardview10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),logout.class);
                startActivity(intent);
            }
        });












      return  mview ;
    }
}