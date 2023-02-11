package com.environment.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signinact extends AppCompatActivity {
ImageView back ;
TextView register ;
EditText emailsign , passsign ;
Button startsign ;
FirebaseAuth mauth ;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinact);
        Init();


        loading = new ProgressDialog(signinact.this);
        loading.setTitle("Welcome In Book Marks");
        loading.setMessage("Please wait minute");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signinact.this,login.class));
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signinact.this,registeract.class));
                finish();
            }
        });

        startsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailsign.getText().toString().equals("")){
                    emailsign.setError("please don't leave email empty");
                }else if (passsign.getText().toString().equals("")){
                    passsign.setError("please don't leave password empty");
                }else {
                    loading.show();
                    signin();
                }
            }
        });

    }



    public  void Init(){
        back = findViewById(R.id.back1);
        register = findViewById(R.id.Register);
       emailsign = findViewById(R.id.emailsign);
       passsign = findViewById(R.id.passsign);
       startsign = findViewById(R.id.startsign);
       mauth  = FirebaseAuth.getInstance();
    }


    public  void signin(){
        String emailsigne = emailsign.getText().toString();
        String passsigne = passsign.getText().toString();
        mauth.signInWithEmailAndPassword(emailsigne,passsigne)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        loading.dismiss();
                        startActivity(new Intent(signinact.this,homeact.class));
                        finish();
                    }   else {
                        loading.dismiss();
                        Toast.makeText(signinact.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                    }
                });


    }

}