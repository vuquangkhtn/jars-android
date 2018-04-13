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
import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.model.Jar;
import com.example.vuquang.jars.activity.model.MonthlyHistory;
import com.example.vuquang.jars.activity.statistics.adapter.JarAdapter;

import java.util.Calendar;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class StatisticsFragment extends Fragment {
    private RecyclerView rvListJar;
    private JarAdapter mJarAdapter;
    private TextView mTvTotalIncome, mTvTotalExpense;
    private TextView mTvMonth, mTvYear;
    private MonthlyHistory mCurrentHistory = new MonthlyHistory();
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
        long monthlyIncome = mCurrentHistory.monthlyIncome;

        mTvMonth = view.findViewById(R.id.tv_month);
        mTvMonth.setText(monthName[mCurrentHistory.currentMonth.get(Calendar.MONTH)]);
        mTvYear = view.findViewById(R.id.tv_year);
        mTvYear.setText(String.valueOf(mCurrentHistory.currentMonth.get(Calendar.YEAR)));

        rvListJar = view.findViewById(R.id.rv_list_jar);
        rvListJar.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mJarAdapter = new JarAdapter(getContext());
        rvListJar.setAdapter(mJarAdapter);

        mTvTotalIncome = view.findViewById(R.id.tv_total_income);
        mTvTotalIncome.setText(String.valueOf(monthlyIncome));
        mTvTotalExpense = view.findViewById(R.id.tv_total_expenses);
        mTvTotalExpense.setText(String.valueOf(mCurrentHistory.getTotalExpense()));

        updateDataJars();
    }

    private void updateDataJars() {
        mJarAdapter.setData(mCurrentHistory.jarList);
    }
}
