package com.android.criminalintentvv.myactivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.android.criminalintentvv.myfragments.CrimeFragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    public static final String EXTRA_CRIME_ID="com.android.criminalintentvv.crime_id";
    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }

    public static Intent newIntent(Context packageContext,UUID crime_id){
        Intent intent=new Intent(packageContext,CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crime_id);
        return intent;
    }

}
