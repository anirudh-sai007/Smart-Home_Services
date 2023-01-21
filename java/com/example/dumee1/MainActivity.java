package com.example.dumee1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class MainActivity extends AppCompatActivity  {
    FirebaseUser firebaseUser;
    String s2;
    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        Intent s;
        if(firebaseUser!=null)
        {
            s = new Intent(MainActivity.this, Home.class);
        }
        else
        {
            s = new Intent(MainActivity.this, login.class);
        }
        startActivity(s);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


}

/*

  s2= firebaseUser.getPhoneNumber().toString().trim();
        Toast.makeText(MainActivity.this,s2,Toast.LENGTH_LONG).show();
 */