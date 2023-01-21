package com.example.dumee1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class delete_slot extends AppCompatActivity {
    private Button bt1;
    private FirebaseFirestore db;
    private DocumentReference messageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_slot);

        bt1=findViewById(R.id.delete_slot);
        db = FirebaseFirestore.getInstance();
        messageRef = db.collection("cities").document("SF")
                .collection("messages").document("message1").collection("slots_list").document("slots");
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageRef.delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(delete_slot.this,"Slot deleted successfully " ,Toast.LENGTH_SHORT).show();
                            }
                        })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(delete_slot.this,"Slot will be available in "+ e.getMessage() ,Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}