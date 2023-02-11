package com.environment.myapplication.viewhorder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class detaillecat extends RecyclerView.ViewHolder {
    public ImageView imgbook ;
    public TextView namebook , auteur ;
    public Button moredetaille , learnlivre ;
    public detaillecat(@NonNull View itemView) {
        super(itemView);
        imgbook = itemView.findViewById(R.id.imgdelivrecat);
        namebook = itemView.findViewById(R.id.namedelivrecat);
        auteur = itemView.findViewById(R.id.autheurdelivrecat);
        moredetaille = itemView.findViewById(R.id.moredetaille);
        learnlivre = itemView.findViewById(R.id.learnlivrcat);

    }
}
