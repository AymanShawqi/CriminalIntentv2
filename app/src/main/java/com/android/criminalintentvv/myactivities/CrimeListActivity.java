package com.android.criminalintentvv.myactivities;

import android.support.v4.app.Fragment;

import com.android.criminalintentvv.myfragments.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }


}
