package com.example.dumee1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class update_or_delete extends AppCompatActivity {
    private Button bt1,bt2;
    FirebaseStorage firebaseStorage;
    FirebaseFirestore firebaseFirestore;
    StorageReference storageReference;
    DocumentReference documentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_or_delete);

        bt1=findViewById(R.id.updateprofile);
        bt2=findViewById(R.id.deleteprofile);
        firebaseFirestore=FirebaseFirestore.getInstance();
        documentReference = firebaseFirestore.collection("user").document("profile");

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                documentReference.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                if(task.getResult().exists())
                                {
                                    Intent intent1=new Intent(update_or_delete.this,profiledetails.class);
                                    startActivity(intent1);
                                }
                                else
                                {
                                    Intent intent1=new Intent(update_or_delete.this,update_details.class);
                                    startActivity(intent1);
                                }
                            }
                        });
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                deleteprofile();
            }
        });
    }

    private void deleteprofile() {
        AlertDialog.Builder builder=new AlertDialog.Builder(update_or_delete.this);
        builder.setTitle("Delete");
        builder.setMessage("Are You Sure You want to delete profile");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                documentReference.delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
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
}