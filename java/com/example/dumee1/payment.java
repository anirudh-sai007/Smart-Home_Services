package com.example.dumee1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class payment extends AppCompatActivity {
    final int UPI_PAYMENT = 0;
    private static final int TEZ_REQUEST_CODE = 123;
    private Button paybtn;
    ImageView gpay;
    String s,s1,s2,user_no,usa;
     DocumentReference messageRef,path3,d2;
    CollectionReference path2;
    CollectionReference c1;
    private FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    TextView t;
    private static final String GOOGLE_TEZ_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        user_no=firebaseUser.getPhoneNumber();
        path3=db.collection("RECENT_BOOKINGS").document("ACTIVE");
        Map<String, Object> user2 = new HashMap<>();
        user2.put("PHONE", user_no);

        path3.set(user2);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        gpay=findViewById(R.id.googlePayButton);
        paybtn=findViewById(R.id.paybtn);
       // t=findViewById(R.id.testing);
        firebaseAuth=FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        s1=getIntent().getStringExtra("name");
        s2=getIntent().getStringExtra("phno");
        s=getIntent().getStringExtra("slot_booked_time");


       // String userdata=t.getText().toString().trim();

       // Toast.makeText(payment.this,s1+s2+s+userdata,Toast.LENGTH_LONG).show();
        final String eid="9700113203@okbizaxis";
        final String ename="test";
        final String amt="1";

        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  mp.start();
                Uri uri = Uri.parse("upi://pay").buildUpon()
                        .appendQueryParameter("pa", eid)
                        .appendQueryParameter("pn", ename)
                        .appendQueryParameter("mc", "")
                        //.appendQueryParameter("tid", "02125412")
                        .appendQueryParameter("tr", "25584584")
                        .appendQueryParameter("tn", "EASY HOME")
                        .appendQueryParameter("am", String.valueOf(amt))
                        .appendQueryParameter("cu", "INR")

                        .build();
                Intent i = getPackageManager().getLaunchIntentForPackage("net.one97.paytm");
                i.setData(uri);
                startActivity(i);

            }
        });
        gpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("upi://pay").buildUpon()
                        .appendQueryParameter("pa", eid)
                        .appendQueryParameter("pn", ename)
                        .appendQueryParameter("mc", "")
                        //.appendQueryParameter("tid", "02125412")
                        .appendQueryParameter("tr", "25584584")
                        .appendQueryParameter("tn", "EASY HOME")
                        .appendQueryParameter("am", String.valueOf(amt))
                        .appendQueryParameter("cu", "INR")

                        .build();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                intent.setPackage(GOOGLE_TEZ_PACKAGE_NAME);
                startActivityForResult(intent, TEZ_REQUEST_CODE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case TEZ_REQUEST_CODE:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        gpayPaymentDataOperation(dataList);
                    } else {
                        Log.d("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        gpayPaymentDataOperation(dataList);
                    }
                } else {
                    Log.d("UPI", "onActivityResult: " + "Return data is null"); //when user simply back without payment
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    gpayPaymentDataOperation(dataList);
                }
                break;
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.d("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    Log.d("UPI", "onActivityResult: " + "Return data is null"); //when user simply back without payment
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }

    private void gpayPaymentDataOperation(ArrayList<String> data) {

            String str = data.get(0);
            Log.d("gpay", "upiPaymentDataOperation1: " + str);
            String paymentCancel = "";
            if (str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if (equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    } else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                } else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }

            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Map<String,Object> profile=new HashMap<String,Object>();
                profile.put("Slot",s);
                profile.put("Phone", s2);
                profile.put("Name",s1);
                Map<String,Object> count=new HashMap<String,Object>();
                count.put("COUNT",1);
                path2=db.collection("SLOT_LISTS").document(user_no).collection("MY_SLOT");
                path2.add(profile);
                d2=db.collection("BOOKINGS_COUNT").document(s2);
                d2.set(count);
                messageRef = db.collection("SLOT_LISTS").document(s2).collection(user_no).document("MY_SLOT");
                messageRef.set(profile)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(payment.this, "Transaction Successful.", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(payment.this,myslots.class);
                                intent.putExtra("slot_booked_time",s);
                                intent.putExtra("name",s1);
                                intent.putExtra("phno",s2);
                                startActivity(intent);
                            }
                        });




            } else {
                Toast.makeText(payment.this, "Transaction Failed Please Try Again", Toast.LENGTH_SHORT).show();

            }
        }

    private void upiPaymentDataOperation(ArrayList<String> data) {

        String str = data.get(0);
        Log.d("UPIPAY", "upiPaymentDataOperation: "+str);
        String paymentCancel = "";
        if(str == null) str = "discard";
        String status = "";
        String approvalRefNo = "";
        String response[] = str.split("&");
        for (int i = 0; i < response.length; i++) {
            String equalStr[] = response[i].split("=");
            if(equalStr.length >= 2) {
                if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                    status = equalStr[1].toLowerCase();
                }
                else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                    approvalRefNo = equalStr[1];
                }
            }
            else {
                paymentCancel = "Payment cancelled by user.";
            }
        }

        if (status.equals("success")) {
            Toasty.success(payment.this, "Transaction Successful", Toast.LENGTH_SHORT, true).show();


            Log.d("UPI", "responseStr: "+approvalRefNo);

        }
        else if("Payment cancelled by user.".equals(paymentCancel)) {
            Toasty.success(payment.this, "Payment Cancelled By User", Toast.LENGTH_SHORT, true).show();



        }
        else {
            Toasty.success(payment.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT, true).show();


        }
    }

}

/*
firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        user_no= firebaseUser.getPhoneNumber().toString().trim();
        t.setText(user_no);
        usa=t.getText().toString();
        Toast.makeText(payment.this,"user is"+usa,Toast.LENGTH_LONG).show();
        path2=db.collection("RECENT_BOOKINGS").document("ACTIVE");
        Map<String, Object> user2 = new HashMap<>();
        user2.put("PHONE", user_no);
        path2.set(user2);
        Toast.makeText(payment.this,user_no,Toast.LENGTH_LONG).show();
 */