package com.example.dumee1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class reported_users extends AppCompatActivity {
FirebaseFirestore firebaseFirestore;
    FirebaseUser firebaseUser;
DocumentReference d;
    RecyclerView recyclerView;
    FirebaseFirestore db;
    RecyclerAdapter4 adapter;
    ArrayList<ProductsModel3> datalist;
String s3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reported_users);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        s3= firebaseUser.getPhoneNumber().toString().trim();
        recyclerView=findViewById(R.id.recview);
        /* recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));*/
        recyclerView.setLayoutManager(new LinearLayoutManager(reported_users.this,LinearLayoutManager.HORIZONTAL,false));
        datalist=new ArrayList<>();
        adapter=new RecyclerAdapter4(datalist);
        recyclerView.setAdapter(adapter);


        db=FirebaseFirestore.getInstance();
        db.collection("REPORTED USERS").document(s3).collection("REPORTS").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list)
                        {
                            ProductsModel3 obj=d.toObject(ProductsModel3.class);
                            datalist.add(obj);
                        }
                        adapter.notifyDataSetChanged();

                    }
                });

    }
}