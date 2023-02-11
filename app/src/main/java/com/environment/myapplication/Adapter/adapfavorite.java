package com.environment.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.environment.myapplication.BookDetaille;
import com.environment.myapplication.Model.bookmodel;
import com.environment.myapplication.pdfview;
import com.environment.myapplication.viewhorder.favviewhorder;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapfavorite extends RecyclerView.Adapter<favviewhorder> {
    Context context ;
    ArrayList<bookmodel> bookmodels ;
    DatabaseReference datadb ;
    FirebaseAuth mauth ;
    String url ;

    public adapfavorite(Context context, ArrayList<bookmodel> bookmodels) {
        this.context = context;
        this.bookmodels = bookmodels;
        if (context != null) {
            mauth = FirebaseAuth.getInstance();
            datadb = FirebaseDatabase.getInstance(context.getString(R.string.Db_url)).getReference().child("Favorite").child(mauth.getCurrentUser().getUid());
        }
    }

    @NonNull
    @Override
    public favviewhorder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favoritebook,parent,false);
        return  new favviewhorder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull favviewhorder holder, int position) {
        Picasso.get().load(bookmodels.get(position).getImgbook()).into(holder.imgbook);
        holder.namebook.setText(bookmodels.get(position).getNamebook());
        holder.auteur.setText(bookmodels.get(position).getAuteur());
        url = bookmodels.get(position).getUrlpdf() ;
       holder.effacer.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog.Builder mydialog = new AlertDialog.Builder(context);
               mydialog.setTitle("Delete "+bookmodels.get(position).getNamebook());
               mydialog.setMessage("Do you really want to delete "
                       +bookmodels.get(position).getNamebook()+"?");

               mydialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       datadb.child(bookmodels.get(position).getBookid())
                               .removeValue()
                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {
                                       if (task.isSuccessful()){
                                           Toast.makeText(context, "Product Remove", Toast.LENGTH_SHORT).show();

                                       }
                                       else {
                                           Toast.makeText(context, "Error , check your internet connexion", Toast.LENGTH_SHORT).show();
                                       }
                                   }
                               });
                   }
               });

               mydialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.dismiss();
                   }
               });
               mydialog.show();

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


       holder.cardView4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(context, BookDetaille.class);
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
