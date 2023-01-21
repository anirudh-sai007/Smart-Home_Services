package com.example.dumee1;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmentthree#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmentthree extends Fragment  implements  BlockingStep {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String verificationId;
    private ProgressBar progressBar;
    private TextInputEditText et1, et2, et3, et4;
    private TextInputLayout let1, let2, let3, let4, let5;
    private TextView tv1;
    private FirebaseAuth mAuth;
    private EditText phoneNo;
    private EditText otp;
    private Button login;
    private TextView getotp;
    private TextView otpsent;
    private TextView resendOTP;
    private boolean getotpclicked = false;
    private TextView countdowntimer;
    Dialog dialog;
    FirebaseUser user;
    FirebaseFirestore fStore;
    DocumentReference messageRef,path2,path3;

    public Fragmentthree() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragmentthree.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragmentthree newInstance(String param1, String param2) {
        Fragmentthree fragment = new Fragmentthree();
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
        View view = inflater.inflate(R.layout.fragment_fragmentthree, container, false);
        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        otp = view.findViewById(R.id.txtotp);
        getotp = view.findViewById(R.id.get_otp);
        resendOTP = view.findViewById(R.id.resend_otp);
        login  = view.findViewById(R.id.btnLogin);
        otpsent = view.findViewById(R.id.otp_sent);
        countdowntimer = view.findViewById(R.id.countdown_timer);
        et1 = view.findViewById(R.id.uname);
        et2 = view.findViewById(R.id.pwd);
        et3 = view.findViewById(R.id.confirm_password);
        et4 = view.findViewById(R.id.txtPhone);
        let4 = view.findViewById(R.id.fourthet);
        let5 = view.findViewById(R.id.fifthet);
        let1 = view.findViewById(R.id.firstet);
        let2 = view.findViewById(R.id.secondet);
        let3 = view.findViewById(R.id.thirdet);
        otp.setEnabled(false);
        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s55=  et4.getText().toString().trim();
                path3=fStore.collection("App_Users").document(s55);
                path3.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.getResult().exists())
                                {
                                    Toasty.error(getActivity(), "Phone Number Already registered", Toast.LENGTH_SHORT, true).show();


                                }
                                else
                                {
                                    getotpOnclick();
                                }
                            }
                        });

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginOnClick();;

            }
        });
        login.setTextColor(Color.parseColor("#C0BEBE"));
        return view;
    }

    public void loginOnClick(){  //*
        String s1, s2,s3,s4,otp_text;
        s1 = et1.getText().toString().trim();
        s2 = et2.getText().toString().trim();
        s3=  et3.getText().toString().trim();
        s4=  et4.getText().toString().trim();
        otp_text = otp.getText().toString().trim();

        if (!validatename(s1) | !validatepwd(s2,s3) | !validatephno(s4) | !validateotp(otp_text)){
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_wait);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            verifyCode(otp_text);


        }

    }
    private Boolean validatepwd(String s2, String s3) {
        if(s2.isEmpty()  )
        {
            let2.setError("Field Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let2);
            return  true;
        }
        else if(s3.isEmpty() )
        {
            let3.setError("Field Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let3);
            return  true;
        }
        else  if(!s3.equals(s2))
        {
            let3.setError("Password does'nt match");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let3);
            return  true;
        }
        else if(s2.length()<6)
        {
            let2.setError("Password length must be minimum 6 characters");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let2);
            return  true;
        }
        else if(s2.length()>10)
        {
            let2.setError("Password length must be maximum 10 characters");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(et2);
            return  true;
        }
        else {
            return false;
        }
    }

    private Boolean validatephno(String s4) {
        if(s4.isEmpty() )
        {
            let4.setError("Field Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let4);
            return  true;
        }
        else if(s4.length()>10)
        {
            let4.setError("Incorrect Phone Number");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let4);
            return  true;
        }
        else {
            return false;
        }
    }

    private Boolean validatename(String s1) {
        if(s1.isEmpty()  )
        {
            let1.setError("Field Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let1);
            return  true;
        }
        else {
            return false;
        }
    }
    private Boolean validateotp(String s5) {
        if(s5.isEmpty()  )
        {
            let5.setError("Field Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let5);
            return  true;
        }
        else if(s5.length()!=6  )
        {
            let5.setError("Incorrect OTP");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let5);
            return  true;
        }
        else {
            return false;
        }
    }

    private void verifyCode(String code){ //*

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,code);
        FirebaseAuth.getInstance().getFirebaseAuthSettings().setAppVerificationDisabledForTesting(true);
        signInWithCredential(credential);


    }

    private void signInWithCredential(PhoneAuthCredential credential) {  //*
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user !=null){
                                String name = et1.getText().toString();
                                String password = et2.getText().toString();
                                String phno = et4.getText().toString();
                                path2=fStore.collection("App_Users").document(phno);
                                Map<String, Object> user2 = new HashMap<>();
                                user2.put("Password", password);
                                path2.set(user2);
                                Map<String, Object> user = new HashMap<>();
                                user.put("Name", name);
                                user.put("Password", password);
                                user.put("Phone", phno);


                                messageRef = fStore
                                        .collection("Users").document(phno);
                                messageRef.set(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Intent i = new Intent(getActivity(), Home.class);
                                                i.putExtra("HI",phno);
                                                startActivity(i);

                                            }
                                        });


                            }else {

                                if (dialog != null){

                                    dialog.dismiss();
                                    Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                });
    }

    public void getotpOnclick(){ //*

        if (!getotpclicked){

            String num   = et4.getText().toString().trim();

            if(!validatephno(num) ){

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
            otp.setEnabled(true);
            dialog.dismiss();
            login.setTextColor(Color.parseColor("#000000"));
            otpsent.setText("OTP has been sent yo your Mobile Number");
            otpsent.setVisibility(View.VISIBLE);
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
           String  entered_otpt = otp.getText().toString().trim();
            String code = phoneAuthCredential.getSmsCode();
            if (code .equals(entered_otpt)){

                otp.setText(code);
                verifyCode(code);


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

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

    }

    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}