package com.android.criminalintentvv.myfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.format.DateFormat;

import com.android.criminalintentvv.R;
import com.android.criminalintentvv.myactivities.CrimeActivity;
import com.android.criminalintentvv.utilites.Crime;
import com.android.criminalintentvv.utilites.CrimeLab;

import java.util.Date;
import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private CrimeAdapter mAdapter;
    public CrimeListFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);
        mRecyclerView=view.findViewById(R.id.crime_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpdateUI();
        return view;
    }

    private void UpdateUI(){
        List<Crime> crimes= CrimeLab.getCrimeLab(getActivity()).getCrimes();
        mAdapter=new CrimeAdapter(crimes);
        mRecyclerView.setAdapter(mAdapter);
    }

    public class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;
        private Crime mCrime;

        //View Holder Class
        public CrimeHolder(LayoutInflater layoutInflater,ViewGroup parent,int layout) {
            super(layoutInflater.inflate(layout,parent,false));
            mTitleTextView=itemView.findViewById(R.id.crime_title);
            mDateTextView=itemView.findViewById(R.id.crime_date);
            mSolvedImageView =itemView.findViewById(R.id.crime_solved);
            itemView.setOnClickListener(this);
        }
        public void bind(Crime crime){
            mCrime=crime;
            mTitleTextView.setText(crime.getTitle());
            mDateTextView.setText(formatCrimeDate(crime.getDate()));
            mSolvedImageView.setVisibility((crime.isSolved())? View.VISIBLE : View.GONE);
        }
        private String formatCrimeDate(Date date){
            String day=DateFormat.format("EEEE",date).toString();
            String dateString=DateFormat.getMediumDateFormat(getActivity()).format(date);
            return String.format("%s, %s",day,dateString);
        }
        @Override
        public void onClick(View v) {
            Intent intent=CrimeActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);
        }
    }


//CrimeAdapter class
    public class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes=crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
                return new CrimeHolder(layoutInflater,parent,R.layout.list_item_crime);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime=mCrimes.get(position);
            holder.bind(crime);
        }


        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

}
