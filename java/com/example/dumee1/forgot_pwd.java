package com.example.dumee1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class forgot_pwd extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framelayout, new forgot_pwd_screen1());
        ft.commit();

    }
}

/*
mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        et1 = findViewById(R.id.login_Phone);
        let1 = findViewById(R.id.userphone);
        bt1=findViewById(R.id.LoginButton);
        getotp = findViewById(R.id.get_otp);
        resendOTP =findViewById(R.id.resend_otp);
        countdowntimer = findViewById(R.id.countdown_timer);
        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1 = et1.getText().toString().trim();
                if(!validatephno(s1)  )
                {
                    path2 = fStore.collection("Customers").document(s1);
                    path2.get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists())
                                    {
                                        getotpOnclick();
                                    }
                                    else
                                    {
                                        Toast.makeText(forgot_pwd.this,"Phone Number Not registered",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }




    public void getotpOnclick(){

        if (!getotpclicked){

            String num   = et1.getText().toString().trim();

            if(num.length() != 10){

                let1.setError("Incorrect Phone Number");
                YoYo.with(Techniques.Shake)
                        .duration(200)
                        .repeat(1)
                        .playOn(let1);
            }else {

                getotpclicked = true;
                String phoneNumber = "+91" + num;
                sendVerificationCode(phoneNumber);
                getotp.setTextColor(Color.parseColor("#C0BEBE"));
                dialog = new Dialog(forgot_pwd.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_wait);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();


            }

        }


    }

    private void sendVerificationCode(String phoneNumber) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallBack)
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);


    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


        @Override
        public void onCodeSent(@NonNull String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
           bt1.setVisibility(View.VISIBLE);
            dialog.dismiss();
            resendOTP.setVisibility(View.VISIBLE);
            bt1.setTextColor(Color.parseColor("#000000"));
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;

            countdowntimer.setVisibility(View.VISIBLE);

            new CountDownTimer(60000,1000){


                @Override
                public void onTick(long millisUntilFinished) {

                    countdowntimer.setText("" + millisUntilFinished/1000);

                }

                @Override
                public void onFinish() {

                    resendOTP.setVisibility(View.VISIBLE);
                    countdowntimer.setVisibility(View.INVISIBLE);

                }
            }.start();
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {




        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            getotpclicked = false;
            getotp.setTextColor(Color.GREEN);
            Toast.makeText(forgot_pwd.this,e.getMessage(),Toast.LENGTH_LONG).show();

        }
    };


    private boolean validatephno(String s4) {

        if(s4.isEmpty() )
        {
            let1.setError("Fied Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let1);
            return  true;
        }
        else if(s4.length()!=10)
        {
            let1.setError("Incorrect Phone Number");
            YoYo.with(Techniques.Shake)
                    .duration(200)
                    .repeat(1)
                    .playOn(let1);
            return  true;
        }
        else {
            return false;
        }
    }
}
 */