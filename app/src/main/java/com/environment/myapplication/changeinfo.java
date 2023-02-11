package com.environment.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class changeinfo extends AppCompatActivity {
ImageView back , changeimagepro ;
Switch aSwitch , switch2;
CardView changecard ;
Boolean switchsimle , switchsmile2 ;
EditText changenameuser ;
Button modifier ;
Uri ImageFIle ;
DatabaseReference datadb ;
FirebaseAuth mauth ;
    SharedPreferences myshered ;
    SharedPreferences jibmlsheared ;
    SharedPreferences.Editor  editor ;
StorageReference imgref ;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeinfo);
        init();
        myshered = getSharedPreferences("myshered", MODE_PRIVATE);
        jibmlsheared =getSharedPreferences("myshered",0);
        editor = myshered.edit();

        loading = new ProgressDialog(changeinfo.this);
        loading.setTitle("Welcome In Book Marks");
        loading.setMessage("Please wait minute");

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
                            changeimagepro.setImageURI(ImageFIle);
                        }
                    }
                });

        // LA FIN DE CE VARIABLE
        switchsimle = false ;
        switchsmile2 = false ;

back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction  = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.r12,new profilfrag());
        finish();
    }
});




        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchsimle = aSwitch.isChecked();
                if (switchsimle.equals(true)) {
                     changecard.setVisibility(View.VISIBLE);

                    changeimagepro.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openGalleryResult.launch(opengalerie());
                        }
                    });
                } else if (switchsimle.equals(false)){
                    changecard.setVisibility(View.GONE);
                }

            }
        });





        switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchsmile2 = switch2.isChecked();
                if (switchsmile2.equals(true)) {
                    changenameuser.setVisibility(View.VISIBLE);


                } else if (switchsmile2.equals(false)){
                    changenameuser.setVisibility(View.GONE);
                }
            }
        });



        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();
                loading.show();
                 if (switchsimle.equals(true)){
                     if (switchsmile2.equals(true) && changenameuser.getText().toString().equals("") ){
                         loading.dismiss();
                         changenameuser.setError("Please Don't Leave name empty");

                     }else {
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
                                                             datadb.child("imageprofil").setValue(y);
                                                             loading.dismiss();
                                                         }
                                                     });


                                         }else {
                                             Toast.makeText(changeinfo.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                         }

                                     }
                                 });
                     }
                 }
                 if (switchsmile2.equals(true)){
                     if (switchsimle.equals(false)){
                         datadb.child("nameuser").setValue(changenameuser.getText().toString());
                         loading.dismiss();
                         Toast.makeText(changeinfo.this, "Name Change", Toast.LENGTH_SHORT).show();
                     } else if (switchsimle.equals(true)){
                         datadb.child("nameuser").setValue(changenameuser.getText().toString());
                     }

                 }

            }
        });





    }

    public void init(){
        back = findViewById(R.id.back2);
     aSwitch = findViewById(R.id.switch1);
     changecard = findViewById(R.id.changecard);
     switch2 = findViewById(R.id.switch2);
     changenameuser = findViewById(R.id.changenameuser);
     modifier = findViewById(R.id.Modifier);
     changeimagepro = findViewById(R.id.changeimgpro);
     mauth = FirebaseAuth.getInstance();
     datadb = FirebaseDatabase.getInstance(getString(R.string.Db_url)).getReference().child("users").child(mauth.getCurrentUser().getUid());
        imgref = FirebaseStorage.getInstance().getReference().child("imgprofil");

    }


    public  Intent opengalerie(){
        Intent i  = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        return i;
    }


}