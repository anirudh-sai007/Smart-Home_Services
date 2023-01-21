package com.example.dumee1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TwoFragment extends Fragment implements BlockingStep {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextInputEditText et1, et2, et3,et4;
    private TextInputLayout let1, let2, let3;


    public TwoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TwoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TwoFragment newInstance(String param1, String param2) {
        TwoFragment fragment = new TwoFragment();
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
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {


            callback.goToNextStep();



    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }

    private boolean validatepwd(String s1, String s2, String s3) {
        if (s2.isEmpty() && s3.isEmpty() && s1.isEmpty()) {
            let2.setError("Field Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let2);
            let3.setError("Field Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let3);
            let1.setError("Field Required");
            YoYo.with(Techniques.Shake)
                    .duration(200)
                    .repeat(1)
                    .playOn(let1);
            return true;
        }
        if (s2.isEmpty() && s3.isEmpty() && !s1.isEmpty()) {
            let2.setError("Field Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let2);
            let3.setError("Field Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let3);
            return true;
        }
        if (!s2.isEmpty() && s3.isEmpty() && s1.isEmpty()) {
            let3.setError("Field Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let3);
            let1.setError("Field Required");
            YoYo.with(Techniques.Shake)
                    .duration(200)
                    .repeat(1)
                    .playOn(let1);
            return true;
        }
        if (!s2.isEmpty() && s3.isEmpty() && s1.isEmpty()) {
            let3.setError("Field Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let3);
            let1.setError("Field Required");
            YoYo.with(Techniques.Shake)
                    .duration(200)
                    .repeat(1)
                    .playOn(let1);
            return true;
        }
        if (s1.isEmpty()) {
            let1.setError("Field Required");
            YoYo.with(Techniques.Shake)
                    .duration(200)
                    .repeat(1)
                    .playOn(let1);
            return true;
        }
        else if (s2.isEmpty())
        {
            let2.setError("Field Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let2);
            return true;
        }
        else if (s3.isEmpty())
        {
            let3.setError("Field Required");
            YoYo.with(Techniques.Bounce)
                    .duration(200)
                    .repeat(1)
                    .playOn(let3);
            return true;
        }
        else if (!s3.equals(s2))
        {
            let3.setError("Password does'nt match");
            YoYo.with(Techniques.Shake)
                    .duration(200)
                    .repeat(1)
                    .playOn(let3);
            return true;
        }
        else if (s2.length() < 6)
        {
            let2.setError("Password length must be minimum 6 characters");
            YoYo.with(Techniques.Shake)
                    .duration(200)
                    .repeat(1)
                    .playOn(let2);
            return true;
        }
        else if (s2.length() > 10)
        {
            let2.setError("Password length must be maximum 10 characters");
            YoYo.with(Techniques.Shake)
                    .duration(200)
                    .repeat(1)
                    .playOn(let2);
            return true;
        }
        else {
            return false;
        }

    }



}