package com.example.dumee1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class myslots extends AppCompatActivity {
    FirebaseFirestore db;
    DocumentReference path3;
    CollectionReference path2;
    FirebaseUser firebaseUser;
    String s2,sno;
    TextView ti;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mycontainer, new wait());
        ft.commit();
        setContentView(R.layout.activity_myslots);
        ti=findViewById(R.id.textViewmkl);
        db=FirebaseFirestore.getInstance();
        sno=getIntent().getStringExtra("phno");
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        s2=firebaseUser.getPhoneNumber();
        ti.setText(s2);
        ti.setVisibility(View.INVISIBLE);


        Toast.makeText(myslots.this, "HI"+ti.getText().toString(), Toast.LENGTH_SHORT).show();
        path2=db.collection("SLOT_LISTS").document(ti.getText().toString()).collection("MY_SLOT");
        path2.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult().isEmpty())
                        {

                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.mycontainer, new e());
                            ft.commit();
                        }
                        else
                        {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.mycontainer, new slot_full());
                            ft.commit();

                        }
                    }
                });

        Toast.makeText(myslots.this,sno,Toast.LENGTH_LONG).show();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bnm);
        bottomNavigationView.setSelectedItemId(R.id.item5);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.item2:
                        startActivity(new Intent(getApplicationContext(), bookslot.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.item3:
                        startActivity(new Intent(getApplicationContext(), favorites.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.item4:
                        startActivity(new Intent(getApplicationContext(), myaccount.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.item5:
                        return true;
                }
                return false;
            }
        });
    }
}

/*

 */