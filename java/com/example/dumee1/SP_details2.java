package com.example.dumee1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SP_details2 extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    CollectionReference documentReference;
    FirebaseUser firebaseUser;
    String s1,s2,s3;
    TextView tv1,tv2;
    Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_p_details2);
        s1=getIntent().getStringExtra("name");
        s2=getIntent().getStringExtra("phno");

        s3=getIntent().getStringExtra("Slot");
        tv1=findViewById(R.id.details_name);
        tv2=findViewById(R.id.details_phno);
        tv1.setText(s1);
        tv2.setText(s2);
        firebaseFirestore=FirebaseFirestore.getInstance();




    }
}