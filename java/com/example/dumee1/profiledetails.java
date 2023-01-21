package com.example.dumee1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class profiledetails extends AppCompatActivity {
    UploadTask uploadTask;
    ProgressBar progressBar;
    FirebaseStorage firebaseStorage;
    FirebaseFirestore firebaseFirestore;
    StorageReference storageReference;
    DocumentReference documentReference;
    CircleImageView profileImageView;
    TextView tv1,tv2,tv3;
    ImageView profile;
    FloatingActionButton floatingActionButton;
    FirebaseUser firebaseUser;
    String s3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiledetails);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore=FirebaseFirestore.getInstance();
        storageReference = firebaseStorage.getInstance().getReference("profile image");
        s3= firebaseUser.getPhoneNumber().toString().trim();
        firebaseFirestore=FirebaseFirestore.getInstance();
        documentReference = firebaseFirestore.collection("Service Provider").document("Service").collection("Painter").document(s3);
        profile = findViewById(R.id.profilepic);
        floatingActionButton=findViewById(R.id.floating_button);
        profileImageView = findViewById(R.id.profileImageView);
        tv1 = findViewById(R.id.retrieve_name);
        tv2 = findViewById(R.id.retrieve_pwd);
        tv3 = findViewById(R.id.retrieve_phno);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(profiledetails.this,update_details.class);
                startActivity(intent1);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(task.getResult().exists())
                        {
                            String name=task.getResult().getString("Name");
                            String pwd=task.getResult().getString("Password");
                            String phno=task.getResult().getString("Phone Number");
                            String urll=task.getResult().getString("Url");

                           // Picasso.get().load(urll).into(profile);
                            try {
                                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(urll));
                                profileImageView.setImageBitmap(bitmap);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            tv1.setText(name);
                            tv2.setText(pwd);
                            tv3.setText(phno);

                        }
                        else
                        {
                            Toast.makeText(profiledetails.this,"Update Profile",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }
}