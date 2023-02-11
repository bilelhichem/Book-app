package com.environment.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.environment.myapplication.BookDetaille;
import com.environment.myapplication.Model.bookmodel;
import com.environment.myapplication.pdfview;
import com.environment.myapplication.viewhorder.detaillecat;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class detailecatadap extends RecyclerView.Adapter<detaillecat> {
    Context context ;
    ArrayList<bookmodel> bookmodels ;

    public detailecatadap(Context context, ArrayList<bookmodel> bookmodels) {
        this.context = context;
        this.bookmodels = bookmodels;
    }

    @NonNull
    @Override
    public detaillecat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.catview,parent,false);
        return  new detaillecat(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull detaillecat holder, int position) {
        Picasso.get().load(bookmodels.get(position).getImgbook()).into(holder.imgbook);
        holder.namebook.setText(bookmodels.get(position).getNamebook());
        holder.auteur.setText(bookmodels.get(position).getAuteur());

        holder.moredetaille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookDetaille.class);
                intent.putExtra("bookid",bookmodels.get(position).getBookid());
                context.startActivity(intent);
            }
        });


        holder.learnlivre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, pdfview.class);
                intent.putExtra("bookid",bookmodels.get(position).getBookid());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookmodels.size();
    }
}
