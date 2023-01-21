package com.example.dumee1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SP_details extends AppCompatActivity implements SmileRating.OnSmileySelectionListener, SmileRating.OnRatingSelectedListener {
    FirebaseFirestore firebaseFirestore;
    CollectionReference documentReference;
    private static final String TAG = "MainActivity";
    CollectionReference d;
    DocumentReference d2;
    FirebaseUser firebaseUser;
    String s1,s2,s3,s4;
    TextView tv1,tv2,tv3;
    Button bt1,bt2,bt3,bt4;
    private SmileRating mSmileRating;
    TextToSpeech textToSpeech;
    LikeButton likeButton;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        s3= firebaseUser.getPhoneNumber().toString().trim();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_p_details);
        s1=getIntent().getStringExtra("name");
        s2=getIntent().getStringExtra("phno");


        likeButton=findViewById(R.id.star_button);
        tv1=findViewById(R.id.details_name);
        tv2=findViewById(R.id.details_phno);
       // tv3=findViewById(R.id.bookings_count);
        bt1=findViewById(R.id.slotbooking);

        bt3=findViewById(R.id.report);
        bt4=findViewById(R.id.rateus);
        mSmileRating = (SmileRating) findViewById(R.id.ratingView);
        mSmileRating.setOnSmileySelectionListener(SP_details.this);
        mSmileRating.setOnRatingSelectedListener(SP_details.this);


        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                documentReference = firebaseFirestore.collection("Favorites").document(s3).collection("favorites");
                Map<String, Object> user2 = new HashMap<>();
                user2.put("Phone", s2);
                user2.put("Name", s1);
                documentReference.add(user2)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(SP_details.this,"Favorites Added",Toast.LENGTH_SHORT).show();

                            }
                        });

            }

            @Override
            public void unLiked(LikeButton likeButton) {
                documentReference = firebaseFirestore.collection("Favorites").document(s3).collection("favorites");
                documentReference.get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                Toast.makeText(SP_details.this,"Favorites Removed",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
       /*

        d2=firebaseFirestore.collection("BOOKINGS_COUNT").document(s2);
        d2.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult().exists())
                        {

                        }
                        else
                        {

                            textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                                @Override
                                public void onInit(int status) {


                                }
                            });
                            textToSpeech.speak("You Are The First User To Book This Service",TextToSpeech.QUEUE_FLUSH,null);
                        }
                    }
                });
        */
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final FlatDialog flatDialog = new FlatDialog(SP_details.this);
                flatDialog.setBackgroundColor(Color.parseColor("#1a2849"))
                        .setTitle("REPORT")
                        .setTitleColor(Color.parseColor("#ffa947"))
                        .setFirstTextFieldHint("Write Here !")
                        .setFirstButtonColor(Color.parseColor("#55eb34"))
                        .setFirstButtonTextColor(Color.parseColor("#FFFFFF"))
                        .setFirstButtonText("SUBMIT")
                        .setSecondButtonColor(Color.parseColor("#f56c5d"))
                        .setSecondButtonTextColor(Color.parseColor("#FFFFFF"))
                        .setSecondButtonText("CANCEL")

                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                               String g=flatDialog.getFirstTextField().toString();
                                Map<String, Object> user2 = new HashMap<>();
                                user2.put("Name",s1);
                                user2.put("Phone",s2);
                                user2.put("REPORT",g);
                                d=firebaseFirestore.collection("REPORTED USERS").document(s3).collection("REPORTS");
                                d.add(user2)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                flatDialog.dismiss();
                                            }
                                        });


                            }
                        })
                        .withSecondButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog.dismiss();
                            }
                        })

                        .show();
            }
        });
        tv1.setText(s1);
        tv2.setText(s2);
        firebaseFirestore=FirebaseFirestore.getInstance();
        //documentReference = firebaseFirestore.collection("Service Provider").document("Service").collection(s2).document(s1).collection("PROFILE_PICTURE").document("PIC").collection("DP");

   bt1.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent it=new Intent(SP_details.this,book_slot_fromsp.class);
           it.putExtra("name",s1);
           it.putExtra("phno",s2);
           startActivity(it);
       }
   });
bt4.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
mSmileRating.setVisibility(View.VISIBLE);
    }
});

    }



    @Override
    public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
        switch (smiley) {
            case SmileRating.BAD:
                s4+="2";
                Toast.makeText(SP_details.this,"2", Toast.LENGTH_SHORT).show();
                break;
            case SmileRating.GOOD:
                s4+="4";
                Toast.makeText(SP_details.this,"4", Toast.LENGTH_SHORT).show();
                break;
            case SmileRating.GREAT:
                s4+="5";
                Toast.makeText(SP_details.this,"5", Toast.LENGTH_SHORT).show();
                break;
            case SmileRating.OKAY:
                s4+="3";
                Toast.makeText(SP_details.this,"3", Toast.LENGTH_SHORT).show();
                break;
            case SmileRating.TERRIBLE:
                s4+="1";
                Toast.makeText(SP_details.this,"1", Toast.LENGTH_SHORT).show();
                break;
            case SmileRating.NONE:
                Log.i(TAG, "None");
                break;
        }
    }



    @Override
    public void onRatingSelected(int level, boolean reselected) {

    }
}