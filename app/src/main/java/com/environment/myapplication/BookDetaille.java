package com.environment.myapplication;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookDetaille extends AppCompatActivity {
ImageView  bake , img;
TextView nmbk , atbk , cntdes ;
String bookid ;
Button learnbook  , dowland ;
DatabaseReference datadb ;
String url ;
private static final int PERMISSION_STORAGE_CODE = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detaille);
          Init();

          bake.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  FragmentTransaction fragmentTransaction  = getSupportFragmentManager().beginTransaction();
                  fragmentTransaction.replace(R.id.maincont,new homefrag());
                  finish();

              }
          });



          bookid = getIntent().getStringExtra("bookid");
        learntodata();


       learnbook.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(BookDetaille.this,pdfview.class);
               intent.putExtra("bookid",bookid);
               startActivity(intent);

           }
       });







       dowland.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                   String [] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

                   requestPermissions(permissions,PERMISSION_STORAGE_CODE);
                }else {
                   startDawnloading();
               }
           }

       });



    }

    public  void Init(){
        bake = findViewById(R.id.bike);
        datadb = FirebaseDatabase.getInstance(getString(R.string.Db_url)).getReference().child("bookpdf");
        nmbk = findViewById(R.id.name0);
        atbk = findViewById(R.id.auth0);
        img = findViewById(R.id.img);
        cntdes = findViewById(R.id.klamdesc);
        learnbook = findViewById(R.id.learnbook);
        dowland = findViewById(R.id.telechargerbook);
    }

public void learntodata(){
    ArrayList bookmodels = new ArrayList<>();
    datadb.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            bookmodels.clear();
            snapshot.child(bookid).getChildren().forEach(name ->{
                bookmodels.add(name.getValue());
            });

            for (int i = 0 ; i< bookmodels.size();i++) {
                nmbk.setText(bookmodels.get(5).toString());
                atbk.setText(bookmodels.get(0).toString());
                cntdes.setText(bookmodels.get(3).toString());
                Picasso.get().load(bookmodels.get(4).toString()).into(img);
                url = bookmodels.get(6).toString();
            }

            }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });



}







    private void startDawnloading() {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Downloading file......");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"" + System.currentTimeMillis());

        DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode , permissions, grantResults);
        switch (requestCode){
            case PERMISSION_STORAGE_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED){
                    startDawnloading();
                }else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}