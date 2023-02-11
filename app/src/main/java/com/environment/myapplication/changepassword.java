package com.environment.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class changepassword extends AppCompatActivity {
ImageView bake3 ;
Switch switchpass ;
EditText changepassword ;
Boolean switchp ;
FirebaseAuth mauth ;
FirebaseUser user ;
    ProgressDialog loading;
    Button modifierpass ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        init();
        loading = new ProgressDialog(changepassword.this);
        loading.setTitle("Welcome In Book Marks");
        loading.setMessage("Please wait minute");
           switchp = false ;
        bake3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction  = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.r13,new settingfrag());
                finish();
            }
        });

        switchpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchp = switchpass.isChecked();
                if (switchp.equals(true)){
                    changepassword.setVisibility(View.VISIBLE);
                }
                else {
                    changepassword.setVisibility(View.GONE);
                }

            }
        });


        modifierpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchp.equals(true)){
                    loading.show();
                    user.updatePassword(changepassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                loading.dismiss();
                                Toast.makeText(changepassword.this, "Password update", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                loading.dismiss();
                                Toast.makeText(changepassword.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(changepassword.this, "Aucun chose Modifier", Toast.LENGTH_SHORT).show();
                }



            }
        });







    }



    public void init(){
         modifierpass = findViewById(R.id.modifierpass);
        bake3 = findViewById(R.id.back3);
        switchpass = findViewById(R.id.switchpass);
     changepassword = findViewById(R.id.changepassword);
     mauth = FirebaseAuth.getInstance();
     user = FirebaseAuth.getInstance().getCurrentUser();

    }




}