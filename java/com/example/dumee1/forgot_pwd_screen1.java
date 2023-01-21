package com.example.dumee1;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link forgot_pwd_screen1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class forgot_pwd_screen1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextInputEditText et1, et2;
    private TextInputLayout let1, let2;
    FirebaseFirestore fStore;
    DocumentReference path1, path2;
    private Button login;
    private TextView getotp;
    private TextView otpsent;
    private TextView resendOTP;
    private boolean getotpclicked = false;
    private TextView countdowntimer;
    Dialog dialog;
    private String verificationId;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    private String eq, se;
    private int x = 5, y = 5;

    public forgot_pwd_screen1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment forgot_pwd_screen1.
     */
    // TODO: Rename and change types and number of parameters
    public static forgot_pwd_screen1 newInstance(String param1, String param2) {
        forgot_pwd_screen1 fragment = new forgot_pwd_screen1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_pwd_screen1, container, false);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        getotp = view.findViewById(R.id.get_otp);
        resendOTP = view.findViewById(R.id.resend_otp);
        login = view.findViewById(R.id.btnLogin);
        countdowntimer = view.findViewById(R.id.countdown_timer);
        et1 = view.findViewById(R.id.txtPhone);
        et2 = view.findViewById(R.id.txtotp);
        let1 = view.findViewById(R.id.let4);
        let2 = view.findViewById(R.id.Password_text_inputLayout);

        login.setVisibility(View.VISIBLE);
        login.setEnabled(false);
        et2.setEnabled(false);
        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getotpOnclick();

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Bundle bl=new Bundle();
                String s=et1.getText().toString().trim();
                bl.putString("Phono",s);
                forgot_pwd_screen2 fragment2 = new forgot_pwd_screen2();
                fragment2.setArguments(bl);
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.card_flip_left, R.anim.card_flip_left_out, R.anim.card_flip_right, R.anim.card_flip_right_out)
                        .replace(R.id.framelayout, fragment2)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();

            }
        });
        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getotpclicked = false;

                resendOTP.setVisibility(View.INVISIBLE);

                getotpOnclick();
            }
        });
        return view;
    }
    public void loginOnClick(){  //*
        String s1, s2,s3,s4,otp_text;
        s1 = et1.getText().toString().trim();
        s2 = et2.getText().toString().trim();

        if ( !s1.isEmpty() ){
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_wait);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();



        }

    }






    public void getotpOnclick(){ //*



            String num   = et1.getText().toString().trim();

            if(!num.isEmpty() ){

                getotpclicked = true;
                String phoneNumber = "+91" + num;
                sendVerificationCode(phoneNumber);
                getotp.setTextColor(Color.parseColor("#C0BEBE"));
                dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_wait);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();


            }




    }

    private void sendVerificationCode(String phoneNumber) { //*

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(60L,TimeUnit.SECONDS)
                        .setActivity(getActivity())
                        .setCallbacks(mCallBack)
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);


    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


        @Override
        public void onCodeSent(@NonNull String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {

            dialog.dismiss();
            et2.setEnabled(true);
            login.setEnabled(false);

            login.setTextColor(Color.parseColor("#000000"));
         Toast.makeText(getActivity(),"OTP Sent Successfully",Toast.LENGTH_SHORT).show();
//            otpsent.setVisibility(View.VISIBLE);
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
           String s11 = et1.getText().toString().trim();
            String code = phoneAuthCredential.getSmsCode();
            if (code .equals(s11)){



            }
            else {
                dialog.dismiss();
            }


        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            getotpclicked = false;
            getotp.setTextColor(Color.DKGRAY);
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();

        }
    };

}