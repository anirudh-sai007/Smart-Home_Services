package com.example.dumee1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class user_registration extends AppCompatActivity implements StepperLayout.StepperListener {
    private StepperLayout mStepperLayout;
    private StepperAdapter mStepperAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        mStepperAdapter = new StepperAdapter(getSupportFragmentManager(), this);
        mStepperLayout.setAdapter(mStepperAdapter);
        mStepperLayout.setListener(this);
    }

    @Override
    public void onCompleted(View completeButton) {

    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int newStepPosition) {


    }

    @Override
    public void onReturn() {

    }
}