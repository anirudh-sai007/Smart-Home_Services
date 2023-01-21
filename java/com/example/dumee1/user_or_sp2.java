package com.example.dumee1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class user_or_sp2 extends AppCompatActivity {
    private Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_or_sp2);
        bt1=findViewById(R.id.button2);
        bt2=findViewById(R.id.button3);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1=new Intent(user_or_sp2.this,user_registration.class);
                startActivity(it1);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it2=new Intent(user_or_sp2.this,serviceprovider_registration.class);
                startActivity(it2);
            }
        });
    }
}