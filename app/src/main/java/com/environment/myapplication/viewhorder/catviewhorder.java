package com.environment.myapplication.viewhorder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;


public class catviewhorder extends RecyclerView.ViewHolder {
    public TextView category;
    public ConstraintLayout cartconst ;
    public catviewhorder(@NonNull View itemView) {
        super(itemView);
        category = itemView.findViewById(R.id.namecategory);
            cartconst = itemView.findViewById(R.id.constcat);
    }
}
