package com.environment.myapplication;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URLEncoder;
import java.util.ArrayList;

public class pdfview extends AppCompatActivity {
    WebView webView ;
    DatabaseReference datadb ;
    String bookid ;
    ArrayList bookmodels ;
    String url ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        bookid = getIntent().getStringExtra("bookid");
        init();
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        bookmodels = new ArrayList<>() ;
        datadb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookmodels.clear();
                snapshot.getChildren().forEach(name ->{
                    bookmodels.add(name.getValue());
                });
                url = bookmodels.get(6).toString() ;
                url = URLEncoder.encode(url);
                webView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + url);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


}
    public void init(){
    webView = findViewById(R.id.webView);
        datadb = FirebaseDatabase.getInstance(getString(R.string.Db_url)).getReference().child("bookpdf").child(bookid);
    }


}