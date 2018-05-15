package com.example.vuquang.jars.activity.statistics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.data.db.dao.HistoryDao;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.example.vuquang.jars.activity.statistics.adapter.JarAdapter;
import com.example.vuquang.jars.activity.utils.KeyPref;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class StatisticsFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private RecyclerView rvListJar;
    private JarAdapter mJarAdapter;
    private TextView mTvTotalIncome, mTvTotalExpense;
    private TextView mTvMonth, mTvYear;
    private MonthlyHistory mCurrentHistory;
    String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December" };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_statistics, null);
        init(view);
        return view;
    }

    private void init(View view) {

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mTvMonth = view.findViewById(R.id.tv_month);
        mTvYear = view.findViewById(R.id.tv_year);
        mTvTotalIncome = view.findViewById(R.id.tv_total_income);
        mTvTotalExpense = view.findViewById(R.id.tv_total_expenses);

        rvListJar = view.findViewById(R.id.rv_list_jar);
        rvListJar.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mJarAdapter = new JarAdapter(getContext());
        rvListJar.setAdapter(mJarAdapter);

        mDatabase.child(KeyPref.HISTORY_KEY).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String uid = mAuth.getUid();
                for (DataSnapshot note: dataSnapshot.getChildren()){
                    MonthlyHistory history = note.getValue(MonthlyHistory.class);
                    if(history != null && history.userId.equals(uid)) {
                        updateUI(history);
                        return;
                    }
                }
                updateUI(new MonthlyHistory(uid));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    void updateUI(MonthlyHistory history){
        mCurrentHistory = history;
        mTvMonth.setText(monthName[mCurrentHistory.getCurrentMonth().get(Calendar.MONTH)]);
        mTvYear.setText(String.valueOf(mCurrentHistory.getCurrentMonth().get(Calendar.YEAR)));
        mTvTotalIncome.setText(String.valueOf(mCurrentHistory.monthlyIncome));
        mTvTotalExpense.setText(String.valueOf(mCurrentHistory.getTotalExpense()));
        mJarAdapter.setData(mCurrentHistory);
    }
}
