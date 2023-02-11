package com.environment.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.environment.myapplication.BookDetaille;
import com.environment.myapplication.Model.bookmodel;
import com.environment.myapplication.viewhorder.viewhorderbook;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapterbook extends RecyclerView.Adapter<viewhorderbook> {
    Context context ;
    ArrayList<bookmodel> bookmodels ;
    DatabaseReference datadb ;
    FirebaseAuth mauth;

    public Adapterbook(Context context, ArrayList<bookmodel> bookmodels) {
        this.context = context;
        this.bookmodels = bookmodels;

        if (context != null){
            mauth = FirebaseAuth.getInstance();
            datadb = FirebaseDatabase.getInstance(context.getString(R.string.Db_url)).getReference().child("Favorite").child(mauth.getCurrentUser().getUid());
        }
    }

    @NonNull
    @Override
    public viewhorderbook onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.booklistpdf,parent,false);
        return  new viewhorderbook(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewhorderbook holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get().load(bookmodels.get(position).getImgbook()).into(holder.imgbook);
        holder.namebook.setText(bookmodels.get(position).getNamebook());
///////////////////////////////////////////////////////////////////////


        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             datadb.child(bookmodels.get(position).getBookid())
                     .setValue(new bookmodel(bookmodels.get(position).getNamebook(),
                             bookmodels.get(position).getAuteur(),bookmodels.get(position).getDescripion(),bookmodels.get(position).getUrlpdf()
                            ,bookmodels.get(position).getImgbook(),bookmodels.get(position).getCategory(),bookmodels.get(position).getBookid()
                             )).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(context, "Book Ajoute in Your Favorite", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                         }
                     });


            }
        });



        // ///////////////////////////////////////////////////


        holder.imgbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookDetaille.class) ;
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
