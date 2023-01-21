package com.example.dumee1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class serviceprovider_registration extends AppCompatActivity  implements StepperLayout.StepperListener {
    private StepperLayout mStepperLayout;
    private stepperadapter2 mStepperAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceprovider_registration);
        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        mStepperAdapter2 = new stepperadapter2(getSupportFragmentManager(), this);
        mStepperLayout.setAdapter(mStepperAdapter2);
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