package com.example.dumee1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

public class login extends AppCompatActivity {
    FirebaseFirestore fStore;
    DocumentReference path2;
    private TextInputEditText et1, et2;
    private TextInputLayout let1, let2;
    private  String s1,s2;
    private TextView fp,r;
    private Button bt1;
    private long b;
    private Toast bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fStore = FirebaseFirestore.getInstance();
        et1 = findViewById(R.id.login_Phone);
        et2 = findViewById(R.id.user_confirm_password);
        let1 = findViewById(R.id.userphone);
        let2 = findViewById(R.id.userpwd);
        bt1=findViewById(R.id.LoginButton);
        r=findViewById(R.id.reg);
        fp=findViewById(R.id.forgot_password);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, user_or_sp2.class);
                startActivity(i);
            }
        });
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, forgot_pwd.class);
                startActivity(i);
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1 = et1.getText().toString().trim();
                s2 = et2.getText().toString().trim();
                if(!validatephno(s1) | !validepwd(s2)) {
                    path2 = fStore.collection("App_Users").document(s1);
                    path2.get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists()) {
                                        String pwd = task.getResult().getString("Password");
                                        if (pwd.equals(s2)) {
                                            Intent i = new Intent(login.this, Home.class);
                                            startActivity(i);
                                        } else {


                                            let2.setError("Incorrect Password");
                                            YoYo.with(Techniques.Bounce)
                                                    .duration(200)
                                                    .repeat(1)
                                                    .playOn(let2);
                                        }
                                    }
                                    else
                                    {
                                        let1.setError("Phone Number Not Registered");
                                        YoYo.with(Techniques.Bounce)
                                                .duration(200)
                                                .repeat(1)
                                                .playOn(let1);
                                    }
                                }
                            });
                }
            }
        });

    }

    private boolean validepwd(String s2) {
        if(s2.isEmpty() )
        {
            let2.setError("Fied Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let2);
            return  true;
        }
        else if(s2.length()<6)
        {
            let2.setError("Incorrect Password");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let2);
            return  true;
        }
        else {
            return false;
        }
    }

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
    @Override
    public void onBackPressed() {
        if(b+2000 > System.currentTimeMillis())
        {
            bt.cancel();
            super.onBackPressed();
            return;
        }
        else
        {
            bt=  Toast.makeText(getBaseContext(),"Press Again To Exit",Toast.LENGTH_SHORT);
            bt.show();
        }
        b=System.currentTimeMillis();
    }

    public void soru(View view) {
        Intent ni=new Intent(login.this,user_or_sp.class);
        startActivity(ni);
    }
}