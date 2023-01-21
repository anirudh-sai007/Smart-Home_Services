package com.example.dumee1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class favorites extends AppCompatActivity {
    FirebaseUser firebaseUser;
    String s3;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        s3= firebaseUser.getPhoneNumber().toString().trim();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framelayout2, new wait());
        ft.commit();
        db=FirebaseFirestore.getInstance();
        db.collection("Favorites").document(s3).collection("favorites").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult().isEmpty())
                        {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.framelayout2, new emptyproviders());
                            ft.commit();

                        }
                        else
                        {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.framelayout2, new favorites_list(s3));
                            ft.commit();

                        }
                    }
                });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bnm);
        bottomNavigationView.setSelectedItemId(R.id.item3);
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
                        return true;
                    case R.id.item4:
                        startActivity(new Intent(getApplicationContext(), myaccount.class));
                        overridePendingTransition(100, 100);
                        return true;
                    case R.id.item5:
                        startActivity(new Intent(getApplicationContext(), myslots.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


    }
}

/*

 */