package com.example.dumee1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class myaccount extends AppCompatActivity {
FirebaseUser firebaseUser;
FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
TextView t1,t2;
    private String s1;
    DocumentReference documentReference;
    String s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        s3= firebaseUser.getPhoneNumber().toString().trim();
        firebaseFirestore=FirebaseFirestore.getInstance();
        documentReference = firebaseFirestore.collection("Service Provider").document("Service").collection("Plumber").document(s3);
        firebaseAuth=FirebaseAuth.getInstance();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bnm);
        bottomNavigationView.setSelectedItemId(R.id.item4);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.item2:
                        startActivity(new Intent(getApplicationContext(), bookslot.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.item3:
                        startActivity(new Intent(getApplicationContext(), favorites.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.item4:
                        return true;
                    case R.id.item5:
                        startActivity(new Intent(getApplicationContext(), myslots.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


    }

    public void changeprofile(View view) {

    }



    public void Logout(View view) {
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        AlertDialog.Builder builder=new AlertDialog.Builder(myaccount.this);
        builder.setTitle("Logout");
        builder.setMessage("Are You Sure You want to Log Out");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                firebaseAuth.signOut();
                Intent it=new Intent(myaccount.this,login.class);
                startActivity(it);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    public void recenthistory(View view) {
        Intent it=new Intent(myaccount.this,recent_history.class);
        startActivity(it);
    }

    public void reportedusers(View view) {
        Intent it=new Intent(myaccount.this,reported_users.class);
        startActivity(it);
    }

    public void deleteprofile(View view) {
        Intent it=new Intent(myaccount.this,update_details.class);
        startActivity(it);
    }

    public void updateprofile(View view) {
        Intent it=new Intent(myaccount.this,upadate_profile.class);
        startActivity(it);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser=firebaseAuth.getCurrentUser();
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult().exists()) {
                            String name = task.getResult().getString("Name");
                            String pwd = task.getResult().getString("Password");
                            String phno = task.getResult().getString("Phone");
                         t1.setText(name);
                         t2.setText(phno);
                        }

                        }
                });
    }
}