package com.example.dumee1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link forgot_pwd_screen2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class forgot_pwd_screen2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextInputEditText et2, et3;
    private TextInputLayout  let2, let3;
    private String s1,s2,s3;
    private Button btn_update;
    FirebaseFirestore fStore;
    DocumentReference path1,path2;
    public forgot_pwd_screen2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment forgot_pwd_screen2.
     */
    // TODO: Rename and change types and number of parameters
    public static forgot_pwd_screen2 newInstance(String param1, String param2) {
        forgot_pwd_screen2 fragment = new forgot_pwd_screen2();
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
        View view= inflater.inflate(R.layout.fragment_forgot_pwd_screen2, container, false);
        fStore = FirebaseFirestore.getInstance();
        et2 = view.findViewById(R.id.pwd);
        et3 = view.findViewById(R.id.confirm_password);
        let2 = view.findViewById(R.id.secondet);
        let3 = view.findViewById(R.id.thirdet);
        Bundle b=getArguments();
       s3 = (String) b.get("Phono");
        btn_update = view.findViewById(R.id.update_pwd);


        btn_update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                s1=et2.getText().toString().trim();
                s2=et3.getText().toString().trim();
                Map<String, Object> user2 = new HashMap<>();
                user2.put("Password", s1);

                    Toast.makeText(getActivity(), s2, Toast.LENGTH_SHORT).show();

                    path1 = fStore.collection("App_Users").document(s3);
                    path1.set(user2)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                           Toast.makeText(getActivity(),"Password Updated Successfully",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getActivity(),Home.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            });

            }
        });
        return view;
    }


}
/*
   Bundle bundle1 = new Bundle();
        String  snum = bundle1.getString("PhoneNum");
  private boolean validatenewpwd(String s1, String s2) {
        if(s1.isEmpty() && s2.isEmpty())
        {
            let2.setError("Fied Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let2);
            let3.setError("Fied Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let3);
            return false;
        }
        else  if(s1.isEmpty())
        {
            let2.setError("Fied Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let2);
            return false;
        }
        else  if(s2.isEmpty())
        {
            let3.setError("Fied Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let3);
            return false;
        }
        else  if(s1.length()<6 )
        {
            let2.setError("Minimum Length Is 6");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let2);
            return false;
        }
        else  if(s1.length()>10)
        {
            let2.setError("Maximum Length Is 10");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let2);
            return false;
        }
        else if(!s1.equals(s2))
        {
            let3.setError("Password Does'nt Match");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let3);
            return false;
        }
        else {
            return true;
        }



    }
 */