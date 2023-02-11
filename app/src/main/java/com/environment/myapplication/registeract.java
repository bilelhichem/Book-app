package com.environment.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.environment.myapplication.Model.user;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class registeract extends AppCompatActivity {
ImageView back , imageprofil;
EditText emailreg , passreg, nameuser ;
Button register ;
TextView signin ;
Uri ImageFIle ;
FirebaseAuth mauth ;
DatabaseReference datadb ;
StorageReference imgref ;
ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeract);
        Init();
        loading = new ProgressDialog(registeract.this);
        loading.setTitle("Welcome In Book Marks");
        loading.setMessage("Please wait minute");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(registeract.this,login.class));
                finish();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(registeract.this,signinact.class));
                finish();
            }
        });


// VARIABLE DE L OUVERTURE DE Galerie

        ActivityResultLauncher<Intent> openGalleryResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // Here, no request code
                            Intent data = result.getData();
                            ImageFIle = data.getData();
                            imageprofil.setImageURI(ImageFIle);
                        }
                    }
                });

        // LA FIN DE CE VARIABLE



// L ' OUVERTURE DE GALERIE
        imageprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGalleryResult.launch(opengallery());
            }
        });

// LA FIN DE L ' OUVERTURE DE GALERIE



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailreg.getText().toString().equals("")){
                    emailreg.setError("Please Don't leave email empty");
                } else  if (passreg.getText().toString().equals("")){
                    passreg.setError("Please Don't leave password empty");
                } else if (nameuser.getText().toString().equals("")){
                  nameuser.setError("please Don't leave name empty");

                } else  {
                    loading.show();
                   creeacount();
                }
            }
        });











    }


    public  void Init(){
        back = findViewById(R.id.back1);
        signin = findViewById(R.id.signin);
        imageprofil = findViewById(R.id.imageprofil);
        emailreg = findViewById(R.id.emailsign);
        passreg = findViewById(R.id.passsign);
        register = findViewById(R.id.startreg);
        mauth = FirebaseAuth.getInstance();
        datadb = FirebaseDatabase.getInstance(getString(R.string.Db_url)).getReference().child("users");
        imgref = FirebaseStorage.getInstance().getReference().child("imgprofil");
        nameuser = findViewById(R.id.nameuser);

    }

    public Intent opengallery(){
        Intent i  = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        return i;
    }



    public void  creeacount (){
        String emailrege = emailreg.getText().toString();
        String passrege = passreg.getText().toString();
        mauth.createUserWithEmailAndPassword(emailrege,passrege)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        imgref.child("image" + mauth.getCurrentUser().getUid())
                                .putFile(ImageFIle)
                                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                    if (task.isSuccessful()){
                                        imgref.child("image" + mauth.getCurrentUser().getUid())
                                                .getDownloadUrl()
                                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                      String y  = uri.toString() ;
                                                        saveindatabase(new user(emailrege,y,nameuser.getText().toString()));

                                                    }
                                                });


                                    }else {
                                        Toast.makeText(registeract.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    }

                                    }
                                });







                    }else {
                        Toast.makeText(registeract.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                    }
                });






    }


    public  void saveindatabase(user user){
       datadb.child(mauth.getCurrentUser().getUid())
               .setValue(user)
               .addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                   if (task.isSuccessful()){
                       FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
                           @Override
                           public void onSuccess(String s) {

                               DatabaseReference UsersRef = FirebaseDatabase.getInstance(getString(R.string.Db_url)).getReference()
                                       .child("users")
                                       .child(mauth.getCurrentUser().getUid()).child("token");
                               UsersRef.setValue(s);

                           }
                       });

                       loading.dismiss();
                       startActivity(new Intent(registeract.this,homeact.class));
                       finish();
                   }
                   else
                   {
                       loading.dismiss();
                       Toast.makeText(registeract.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                   }
                   }
               });

    }




}