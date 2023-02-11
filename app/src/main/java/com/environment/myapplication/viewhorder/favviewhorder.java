package com.environment.myapplication.viewhorder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class favviewhorder extends RecyclerView.ViewHolder {
  public   ImageView imgbook ;
   public TextView namebook , auteur ;
  public   Button effacer , learnlivre ;
  public CardView cardView4;
    public favviewhorder(@NonNull View itemView) {
        super(itemView);
        imgbook = itemView.findViewById(R.id.imgdelivre);
        namebook = itemView.findViewById(R.id.namedelivrecat);
        auteur = itemView.findViewById(R.id.autheurdelivrecat);
        effacer = itemView.findViewById(R.id.moredetaille);
        learnlivre = itemView.findViewById(R.id.learnlivr);
     cardView4 = itemView.findViewById(R.id.cardView4);
    }
}
