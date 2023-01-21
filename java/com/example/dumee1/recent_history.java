package com.example.dumee1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class recent_history extends AppCompatActivity {
    FirebaseUser firebaseUser;
    String s3;
    RecyclerView recyclerView;
    FirebaseFirestore db;
    RecyclerAdapter3 adapter;
    ArrayList<ProductsModel2> datalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_history);
        recyclerView=findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(recent_history.this,LinearLayoutManager.HORIZONTAL,false));

        datalist=new ArrayList<>();
        adapter=new RecyclerAdapter3(datalist);
        recyclerView.setAdapter(adapter);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        s3= firebaseUser.getPhoneNumber().toString().trim();
        db=FirebaseFirestore.getInstance();
        db.collection("SLOT_LISTS").document(s3).collection("MY_SLOT").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list)
                        {
                            ProductsModel2 obj=d.toObject(ProductsModel2.class);
                            datalist.add(obj);
                        }
                        adapter.notifyDataSetChanged();

                    }
                });

    }
}