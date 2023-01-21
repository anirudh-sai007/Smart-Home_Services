package com.example.dumee1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class book_slot_fromsp extends AppCompatActivity {
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15;
    String s,s1,s2;
    private FirebaseFirestore db;
    private DocumentReference messageRef;
    private Button book_a_slot;
    FirebaseUser firebaseUser;
    String user_no,spno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_slot_fromsp);
        db = FirebaseFirestore.getInstance();

        tv1=findViewById(R.id.slot1);
        tv2=findViewById(R.id.slot2);
        tv3=findViewById(R.id.slot3);
        tv4=findViewById(R.id.slot4);
        tv5=findViewById(R.id.slot5);

        tv6=findViewById(R.id.slot6);
        tv7=findViewById(R.id.slot7);
        tv8=findViewById(R.id.slot8);
        tv9=findViewById(R.id.slot9);
        tv10=findViewById(R.id.slot10);
        tv11=findViewById(R.id.slot11);
        tv12=findViewById(R.id.slot12);
        tv13=findViewById(R.id.slot13);
        tv14=findViewById(R.id.slot14);
        tv15=findViewById(R.id.slot15);


        book_a_slot=findViewById(R.id.book_slot);
        book_a_slot.setEnabled(false);

        tv1.setOnClickListener(new clickevent());
        tv2.setOnClickListener(new clickevent());
        tv3.setOnClickListener(new clickevent());
        tv4.setOnClickListener(new clickevent());
        tv5.setOnClickListener(new clickevent());
        tv6.setOnClickListener(new clickevent());
        tv7.setOnClickListener(new clickevent());
        tv8.setOnClickListener(new clickevent());
        tv9.setOnClickListener(new clickevent());
        tv10.setOnClickListener(new clickevent());
        tv11.setOnClickListener(new clickevent());
        tv12.setOnClickListener(new clickevent());
        tv13.setOnClickListener(new clickevent());
        tv14.setOnClickListener(new clickevent());
        tv15.setOnClickListener(new clickevent());


        book_a_slot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(book_slot_fromsp.this,payment.class);
                intent.putExtra("name",s1);
                intent.putExtra("phno",s2);
                intent.putExtra("slot_booked_time",s);
                startActivity(intent);
            }
        });

    }
    public class clickevent implements   View.OnClickListener {

        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View v) {

            book_a_slot.setEnabled(true);
            switch (v.getId())
            {
                case R.id.slot1:
                    tv1.setBackgroundColor(R.color.black);
                    tv2.setBackgroundColor(R.color.white);
                    tv3.setBackgroundColor(R.color.white);
                    tv4.setBackgroundColor(R.color.white);
                    tv5.setBackgroundColor(R.color.white);
                    tv6.setBackgroundColor(R.color.white);
                    tv7.setBackgroundColor(R.color.white);
                    tv8.setBackgroundColor(R.color.white);
                    tv9.setBackgroundColor(R.color.white);
                    tv10.setBackgroundColor(R.color.white);
                    tv11.setBackgroundColor(R.color.white);
                    tv12.setBackgroundColor(R.color.white);
                    tv13.setBackgroundColor(R.color.white);
                    tv14.setBackgroundColor(R.color.white);
                    tv15.setBackgroundColor(R.color.white);
                    s=tv1.getText().toString().trim();
                    break;
                case R.id.slot2:
                    tv1.setBackgroundColor(R.color.white);
                    tv2.setBackgroundColor(R.color.black);
                    tv3.setBackgroundColor(R.color.white);
                    tv4.setBackgroundColor(R.color.white);
                    tv5.setBackgroundColor(R.color.white);
                    tv6.setBackgroundColor(R.color.white);
                    tv7.setBackgroundColor(R.color.white);
                    tv8.setBackgroundColor(R.color.white);
                    tv9.setBackgroundColor(R.color.white);
                    tv10.setBackgroundColor(R.color.white);
                    tv11.setBackgroundColor(R.color.white);
                    tv12.setBackgroundColor(R.color.white);
                    tv13.setBackgroundColor(R.color.white);
                    tv14.setBackgroundColor(R.color.white);
                    tv15.setBackgroundColor(R.color.white);

                    s=tv2.getText().toString().trim();
                    break;
                case R.id.slot3:
                    tv1.setBackgroundColor(R.color.white);
                    tv2.setBackgroundColor(R.color.white);
                    tv3.setBackgroundColor(R.color.black);
                    tv4.setBackgroundColor(R.color.white);
                    tv5.setBackgroundColor(R.color.white);
                    tv6.setBackgroundColor(R.color.white);
                    tv7.setBackgroundColor(R.color.white);
                    tv8.setBackgroundColor(R.color.white);
                    tv9.setBackgroundColor(R.color.white);
                    tv10.setBackgroundColor(R.color.white);
                    tv11.setBackgroundColor(R.color.white);
                    tv12.setBackgroundColor(R.color.white);
                    tv13.setBackgroundColor(R.color.white);
                    tv14.setBackgroundColor(R.color.white);
                    tv15.setBackgroundColor(R.color.white);

                    s=tv3.getText().toString().trim();
                    break;


                case R.id.slot4:
                    tv1.setBackgroundColor(R.color.white);
                    tv2.setBackgroundColor(R.color.white);
                    tv3.setBackgroundColor(R.color.white);
                    tv4.setBackgroundColor(R.color.black);
                    tv5.setBackgroundColor(R.color.white);
                    tv6.setBackgroundColor(R.color.white);
                    tv7.setBackgroundColor(R.color.white);
                    tv8.setBackgroundColor(R.color.white);
                    tv9.setBackgroundColor(R.color.white);
                    tv10.setBackgroundColor(R.color.white);
                    tv11.setBackgroundColor(R.color.white);
                    tv12.setBackgroundColor(R.color.white);
                    tv13.setBackgroundColor(R.color.white);
                    tv14.setBackgroundColor(R.color.white);
                    tv15.setBackgroundColor(R.color.white);

                    s=tv4.getText().toString().trim();
                    break;


                case R.id.slot5:
                    tv1.setBackgroundColor(R.color.white);
                    tv2.setBackgroundColor(R.color.white);
                    tv3.setBackgroundColor(R.color.white);
                    tv4.setBackgroundColor(R.color.white);
                    tv5.setBackgroundColor(R.color.black);
                    tv6.setBackgroundColor(R.color.white);
                    tv7.setBackgroundColor(R.color.white);
                    tv8.setBackgroundColor(R.color.white);
                    tv9.setBackgroundColor(R.color.white);
                    tv10.setBackgroundColor(R.color.white);
                    tv11.setBackgroundColor(R.color.white);
                    tv12.setBackgroundColor(R.color.white);
                    tv13.setBackgroundColor(R.color.white);
                    tv14.setBackgroundColor(R.color.white);
                    tv15.setBackgroundColor(R.color.white);

                    s=tv5.getText().toString().trim();
                    break;

                case R.id.slot6:
                    tv1.setBackgroundColor(R.color.white);
                    tv2.setBackgroundColor(R.color.white);
                    tv3.setBackgroundColor(R.color.white);
                    tv4.setBackgroundColor(R.color.white);
                    tv5.setBackgroundColor(R.color.white);
                    tv6.setBackgroundColor(R.color.black);
                    tv7.setBackgroundColor(R.color.white);
                    tv8.setBackgroundColor(R.color.white);
                    tv9.setBackgroundColor(R.color.white);
                    tv10.setBackgroundColor(R.color.white);
                    tv11.setBackgroundColor(R.color.white);
                    tv12.setBackgroundColor(R.color.white);
                    tv13.setBackgroundColor(R.color.white);
                    tv14.setBackgroundColor(R.color.white);
                    tv15.setBackgroundColor(R.color.white);


                    s=tv6.getText().toString().trim();
                    break;

                case R.id.slot7:
                    tv1.setBackgroundColor(R.color.white);
                    tv2.setBackgroundColor(R.color.white);
                    tv3.setBackgroundColor(R.color.white);
                    tv4.setBackgroundColor(R.color.white);
                    tv5.setBackgroundColor(R.color.white);
                    tv6.setBackgroundColor(R.color.white);
                    tv7.setBackgroundColor(R.color.black);
                    tv8.setBackgroundColor(R.color.white);
                    tv9.setBackgroundColor(R.color.white);
                    tv10.setBackgroundColor(R.color.white);
                    tv11.setBackgroundColor(R.color.white);
                    tv12.setBackgroundColor(R.color.white);
                    tv13.setBackgroundColor(R.color.white);
                    tv14.setBackgroundColor(R.color.white);
                    tv15.setBackgroundColor(R.color.white);


                    s=tv7.getText().toString().trim();
                    break;

                case R.id.slot8:
                    tv1.setBackgroundColor(R.color.white);
                    tv2.setBackgroundColor(R.color.white);
                    tv3.setBackgroundColor(R.color.white);
                    tv4.setBackgroundColor(R.color.white);
                    tv5.setBackgroundColor(R.color.white);
                    tv6.setBackgroundColor(R.color.white);
                    tv7.setBackgroundColor(R.color.white);
                    tv8.setBackgroundColor(R.color.black);
                    tv9.setBackgroundColor(R.color.white);
                    tv10.setBackgroundColor(R.color.white);
                    tv11.setBackgroundColor(R.color.white);
                    tv12.setBackgroundColor(R.color.white);
                    tv13.setBackgroundColor(R.color.white);
                    tv14.setBackgroundColor(R.color.white);
                    tv15.setBackgroundColor(R.color.white);


                    s=tv8.getText().toString().trim();
                    break;

                case R.id.slot9:
                    tv1.setBackgroundColor(R.color.white);
                    tv2.setBackgroundColor(R.color.white);
                    tv3.setBackgroundColor(R.color.white);
                    tv4.setBackgroundColor(R.color.white);
                    tv5.setBackgroundColor(R.color.white);
                    tv6.setBackgroundColor(R.color.white);
                    tv7.setBackgroundColor(R.color.white);
                    tv8.setBackgroundColor(R.color.white);
                    tv9.setBackgroundColor(R.color.black);
                    tv10.setBackgroundColor(R.color.white);
                    tv11.setBackgroundColor(R.color.white);
                    tv12.setBackgroundColor(R.color.white);
                    tv13.setBackgroundColor(R.color.white);
                    tv14.setBackgroundColor(R.color.white);
                    tv15.setBackgroundColor(R.color.white);

                    s=tv9.getText().toString().trim();
                    break;



                case R.id.slot10:
                    tv1.setBackgroundColor(R.color.white);
                    tv2.setBackgroundColor(R.color.white);
                    tv3.setBackgroundColor(R.color.white);
                    tv4.setBackgroundColor(R.color.white);
                    tv5.setBackgroundColor(R.color.white);
                    tv6.setBackgroundColor(R.color.white);
                    tv7.setBackgroundColor(R.color.white);
                    tv8.setBackgroundColor(R.color.white);
                    tv9.setBackgroundColor(R.color.white);
                    tv10.setBackgroundColor(R.color.black);
                    tv11.setBackgroundColor(R.color.white);
                    tv12.setBackgroundColor(R.color.white);
                    tv13.setBackgroundColor(R.color.white);
                    tv14.setBackgroundColor(R.color.white);
                    tv15.setBackgroundColor(R.color.white);

                    s=tv10.getText().toString().trim();
                    break;

                case R.id.slot11:
                    tv1.setBackgroundColor(R.color.white);
                    tv2.setBackgroundColor(R.color.white);
                    tv3.setBackgroundColor(R.color.white);
                    tv4.setBackgroundColor(R.color.white);
                    tv5.setBackgroundColor(R.color.white);
                    tv6.setBackgroundColor(R.color.white);
                    tv7.setBackgroundColor(R.color.white);
                    tv8.setBackgroundColor(R.color.white);
                    tv9.setBackgroundColor(R.color.white);
                    tv10.setBackgroundColor(R.color.white);
                    tv11.setBackgroundColor(R.color.black);
                    tv12.setBackgroundColor(R.color.white);
                    tv13.setBackgroundColor(R.color.white);
                    tv14.setBackgroundColor(R.color.white);
                    tv15.setBackgroundColor(R.color.white);

                    s=tv11.getText().toString().trim();
                    break;

                case R.id.slot12:
                    tv1.setBackgroundColor(R.color.white);
                    tv2.setBackgroundColor(R.color.white);
                    tv3.setBackgroundColor(R.color.white);
                    tv4.setBackgroundColor(R.color.white);
                    tv5.setBackgroundColor(R.color.white);
                    tv6.setBackgroundColor(R.color.white);
                    tv7.setBackgroundColor(R.color.white);
                    tv8.setBackgroundColor(R.color.white);
                    tv9.setBackgroundColor(R.color.white);
                    tv10.setBackgroundColor(R.color.white);
                    tv11.setBackgroundColor(R.color.white);
                    tv12.setBackgroundColor(R.color.black);
                    tv13.setBackgroundColor(R.color.white);
                    tv14.setBackgroundColor(R.color.white);
                    tv15.setBackgroundColor(R.color.white);

                    s=tv12.getText().toString().trim();
                    break;

                case R.id.slot13:
                    tv1.setBackgroundColor(R.color.white);
                    tv2.setBackgroundColor(R.color.white);
                    tv3.setBackgroundColor(R.color.white);
                    tv4.setBackgroundColor(R.color.white);
                    tv5.setBackgroundColor(R.color.white);
                    tv6.setBackgroundColor(R.color.white);
                    tv7.setBackgroundColor(R.color.white);
                    tv8.setBackgroundColor(R.color.white);
                    tv9.setBackgroundColor(R.color.white);
                    tv10.setBackgroundColor(R.color.white);
                    tv11.setBackgroundColor(R.color.white);
                    tv12.setBackgroundColor(R.color.white);
                    tv13.setBackgroundColor(R.color.black);
                    tv14.setBackgroundColor(R.color.white);
                    tv15.setBackgroundColor(R.color.white);

                    s=tv13.getText().toString().trim();
                    break;

                case R.id.slot14:
                    tv1.setBackgroundColor(R.color.white);
                    tv2.setBackgroundColor(R.color.white);
                    tv3.setBackgroundColor(R.color.white);
                    tv4.setBackgroundColor(R.color.white);
                    tv5.setBackgroundColor(R.color.white);
                    tv6.setBackgroundColor(R.color.white);
                    tv7.setBackgroundColor(R.color.white);
                    tv8.setBackgroundColor(R.color.white);
                    tv9.setBackgroundColor(R.color.white);
                    tv10.setBackgroundColor(R.color.white);
                    tv11.setBackgroundColor(R.color.white);
                    tv12.setBackgroundColor(R.color.white);
                    tv13.setBackgroundColor(R.color.white);
                    tv14.setBackgroundColor(R.color.black);
                    tv15.setBackgroundColor(R.color.white);

                    s=tv14.getText().toString().trim();
                    break;

                case R.id.slot15:
                    tv1.setBackgroundColor(R.color.white);
                    tv2.setBackgroundColor(R.color.white);
                    tv3.setBackgroundColor(R.color.white);
                    tv4.setBackgroundColor(R.color.white);
                    tv5.setBackgroundColor(R.color.white);
                    tv6.setBackgroundColor(R.color.white);
                    tv7.setBackgroundColor(R.color.white);
                    tv8.setBackgroundColor(R.color.white);
                    tv9.setBackgroundColor(R.color.white);
                    tv10.setBackgroundColor(R.color.white);
                    tv11.setBackgroundColor(R.color.white);
                    tv12.setBackgroundColor(R.color.white);
                    tv13.setBackgroundColor(R.color.white);
                    tv14.setBackgroundColor(R.color.white);
                    tv15.setBackgroundColor(R.color.black);

                    s=tv15.getText().toString().trim();
                    break;




                default:
                    book_a_slot.setEnabled(false);
                    break;
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        user_no=firebaseUser.getPhoneNumber();
        s1=getIntent().getStringExtra("name");
        s2=getIntent().getStringExtra("phno");
        messageRef = db.collection("SLOT_LISTS").document(user_no);
        messageRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(task.getResult().exists())
                        {

                            String slot_time=task.getResult().getString("Slot");
                            String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16;
                            s1=tv1.getText().toString().trim();
                            s2=tv2.getText().toString().trim();
                            s3=tv3.getText().toString().trim();
                            s4=tv4.getText().toString().trim();
                            s5=tv5.getText().toString().trim();
                            s6=tv6.getText().toString().trim();
                            s7=tv7.getText().toString().trim();
                            s8=tv8.getText().toString().trim();
                            s9=tv9.getText().toString().trim();
                            s10=tv10.getText().toString().trim();
                            s11=tv11.getText().toString().trim();
                            s12=tv12.getText().toString().trim();
                            s13=tv13.getText().toString().trim();
                            s14=tv14.getText().toString().trim();
                            s15=tv15.getText().toString().trim();
                            s16="30Min";
                            if(s1.equals(slot_time))
                            {
                                tv1.setEnabled(false);
                                tv1.setBackgroundColor(R.color.slot_full);

                            }

                            if(s2.equals(slot_time))
                            {
                                tv2.setEnabled(false);
                                tv2.setBackgroundColor(R.color.slot_full);
                            }

                            if(s3.equals(slot_time))
                            {
                                tv3.setEnabled(false);
                                tv3.setBackgroundColor(R.color.slot_full);
                            }

                            if(s4.equals(slot_time))
                            {
                                tv4.setEnabled(false);
                                tv4.setBackgroundColor(R.color.slot_full);
                            }

                            if(s5.equals(slot_time))
                            {
                                tv5.setEnabled(false);
                                tv5.setBackgroundColor(R.color.slot_full);
                            }

                            if(s6.equals(slot_time))
                            {

                                tv6.setEnabled(false);
                                tv6.setBackgroundColor(R.color.slot_full);
                            }

                            if(s7.equals(slot_time))
                            {
                                tv7.setEnabled(false);
                                tv7.setBackgroundColor(R.color.slot_full);
                            }

                            if(s8.equals(slot_time))
                            {
                                tv8.setEnabled(false);
                                tv8.setBackgroundColor(R.color.slot_full);
                            }

                            if(s9.equals(slot_time))
                            {
                                tv9.setEnabled(false);
                                tv9.setBackgroundColor(R.color.slot_full);
                            }

                            if(s10.equals(slot_time))
                            {
                                tv10.setEnabled(false);
                                tv10.setBackgroundColor(R.color.slot_full);
                            }

                            if(s11.equals(slot_time))
                            {
                                tv11.setEnabled(false);
                                tv11.setBackgroundColor(R.color.slot_full);
                            }

                            if(s12.equals(slot_time))
                            {
                                tv12.setEnabled(false);
                                tv12.setBackgroundColor(R.color.slot_full);
                            }

                            if(s13.equals(slot_time))
                            {
                                tv13.setEnabled(false);
                                tv13.setBackgroundColor(R.color.slot_full);
                            }

                            if(s14.equals(slot_time))
                            {
                                tv14.setEnabled(false);
                                tv14.setBackgroundColor(R.color.slot_full);
                            }

                            if(s15.equals(slot_time))
                            {
                                tv15.setEnabled(false);
                                tv15.setBackgroundColor(R.color.slot_full);
                            }





                        }
                    }
                });



    }
}
