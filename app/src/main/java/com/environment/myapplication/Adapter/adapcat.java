package com.environment.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.environment.myapplication.Model.bookmodel;
import com.environment.myapplication.viewhorder.catviewhorder;
import com.example.myapplication.R;

import java.util.ArrayList;

public class adapcat extends RecyclerView.Adapter<catviewhorder> {
    Context context ;
    ArrayList<bookmodel> bookmodels ;


    public adapcat(Context context, ArrayList<bookmodel> bookmodels) {
        this.context = context;
        this.bookmodels = bookmodels;

    }

    @NonNull
    @Override
    public catviewhorder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category,parent,false);
        return  new catviewhorder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull catviewhorder holder, int position) {
        holder.category.setText(bookmodels.get(position).getCategory());

        holder.cartconst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,com.environment.myapplication.categorydetaile.class);
                intent.putExtra("category",bookmodels.get(position).getCategory());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookmodels.size();
    }
}
