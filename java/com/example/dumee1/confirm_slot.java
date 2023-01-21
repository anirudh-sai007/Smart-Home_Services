package com.example.dumee1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class confirm_slot extends AppCompatActivity {
    private Button bt1;
    private FirebaseFirestore db;
    private DocumentReference messageRef;
    private String get_slot_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_slot);
        db = FirebaseFirestore.getInstance();
        messageRef = db.collection("cities1").document("SF1");
        bt1=findViewById(R.id.confirm);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                get_slot_time=getIntent().getStringExtra("slot_booked_time");
                Map<String,Object> profie=new HashMap<String,Object>();
                profie.put("slot",get_slot_time);
                messageRef.set(profie)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(confirm_slot.this,"Slot booked Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent2=new Intent(confirm_slot.this,delete_slot.class);
                                startActivity(intent2);
                            }
                        });

            }
        });
    }
}