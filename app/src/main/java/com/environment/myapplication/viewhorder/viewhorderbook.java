package com.environment.myapplication.viewhorder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;


public class viewhorderbook extends RecyclerView.ViewHolder {
 public    ImageView imgbook ,fav;
 public    TextView namebook ;
 public CardView cardbook ;
    public viewhorderbook(@NonNull View itemView) {
        super(itemView);

        imgbook = itemView.findViewById(R.id.imgdebook);
        namebook = itemView.findViewById(R.id.namedebook);
       cardbook = itemView.findViewById(R.id.cardbook);
       fav = itemView.findViewById(R.id.fav);

    }
}
