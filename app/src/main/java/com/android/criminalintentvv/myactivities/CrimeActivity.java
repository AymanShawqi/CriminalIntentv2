package com.android.criminalintentvv.myactivities;

import android.support.v4.app.Fragment;

import com.android.criminalintentvv.myfragments.CrimeFragment;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
