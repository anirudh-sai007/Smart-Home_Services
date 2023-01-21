package com.example.dumee1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class stepperadapter2 extends AbstractFragmentStepAdapter {
    private static final String CURRENT_STEP_POSITION_KEY = "messageResourceId";

    public stepperadapter2(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }


    @Override
    public Step createStep(int position) {
        switch (position){
            case 0:
                final SP_Fragment_1 step1 = new SP_Fragment_1();
                Bundle b1 = new Bundle();
                b1.putInt(CURRENT_STEP_POSITION_KEY, position);
                step1.setArguments(b1);
                return step1;
            case 1:
                final SP_Fragment_2 step2 = new SP_Fragment_2();
                Bundle b2 = new Bundle();
                b2.putInt(CURRENT_STEP_POSITION_KEY, position);
                step2.setArguments(b2);
                return step2;
            case 2:
                final SP_Fragment_3 step3 = new SP_Fragment_3();
                Bundle b3 = new Bundle();
                b3.putInt(CURRENT_STEP_POSITION_KEY, position);
                step3.setArguments(b3);
                return step3;
            case 3:
                final SP_Fragment_4 step4 = new SP_Fragment_4();
                Bundle b4 = new Bundle();
                b4.putInt(CURRENT_STEP_POSITION_KEY, position);
                step4.setArguments(b4);
                return step4;
        }
        return null;
    }
    @Override
    public int getCount() {
        return 4;
    }
    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        switch (position){
            case 0:
                return new StepViewModel.Builder(context)
                        .setTitle("Phone") //can be a CharSequence instead
                        .create();
            case 1:
                return new StepViewModel.Builder(context)
                        .setTitle("Details_1") //can be a CharSequence instead
                        .create();
            case 2:
                return new StepViewModel.Builder(context)
                        .setTitle("Details_2") //can be a CharSequence instead
                        .create();
            case 3:
                return new StepViewModel.Builder(context)
                        .setTitle("OTP") //can be a CharSequence instead
                        .create();
        }
        return null;
    }

}
