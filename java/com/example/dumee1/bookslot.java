package com.example.dumee1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bookslot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookslot);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mycontainer, new carpenters());
        ft.commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bnm);
        bottomNavigationView.setSelectedItemId(R.id.item2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.item2:
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

                        startActivity(new Intent(getApplicationContext(), myslots.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


    }
}