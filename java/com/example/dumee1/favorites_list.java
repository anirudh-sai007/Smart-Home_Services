package com.example.dumee1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link favorites_list#newInstance} factory method to
 * create an instance of this fragment.
 */
public class favorites_list extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    FirebaseFirestore db;
    ProductsModel productsModel;
    RecyclerAdapter adapter;
    ArrayList<ProductsModel> datalist;
    String s3;
    FirebaseUser firebaseUser;
    public favorites_list(String s3) {
        // Required empty public constructor
        this.s3=s3;
    }
    public favorites_list() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment favorites_list.
     */
    // TODO: Rename and change types and number of parameters
    public static favorites_list newInstance(String param1, String param2) {
        favorites_list fragment = new favorites_list();
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
    public void onStart() {
        super.onStart();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favorites_list, container, false);
        recyclerView=view.findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        s3= firebaseUser.getPhoneNumber().toString().trim();
        datalist=new ArrayList<>();
        db=FirebaseFirestore.getInstance();
        db.collection("Favorites").document(s3).collection("favorites").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for(QueryDocumentSnapshot documentSnapshot:value)
                {
                    productsModel=documentSnapshot.toObject(ProductsModel.class);
                    String name=productsModel.getName();
                    String phone=productsModel.getPhone();
                    datalist.add(new ProductsModel(name,phone));
                }
                adapter=new RecyclerAdapter(datalist);
                recyclerView.setAdapter(adapter);

            }
        });



        return  view;
    }
}