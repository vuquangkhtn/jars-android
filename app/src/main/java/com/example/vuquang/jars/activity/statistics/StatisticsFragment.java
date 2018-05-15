package com.example.vuquang.jars.activity.statistics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.base.BaseFragment;
import com.example.vuquang.jars.activity.data.AppDataManager;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.example.vuquang.jars.activity.statistics.adapter.JarAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class StatisticsFragment extends BaseFragment implements StatisticsMvpView{
    private RecyclerView rvListJar;
    private JarAdapter mJarAdapter;
    private TextView mTvTotalIncome, mTvTotalExpense;
    private TextView mTvMonth, mTvYear;

    private StatisticsPresenter<StatisticsMvpView> mPresenter;

    String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December" };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        mPresenter = new StatisticsPresenter<>(new AppDataManager(
                FirebaseDatabase.getInstance().getReference(),
                FirebaseAuth.getInstance()
        ));

        mPresenter.onAttach(this);
        return view;
    }

    @Override
    public void updateUI(MonthlyHistory history) {
        mTvMonth.setText(monthName[history.monthToCalendar().get(Calendar.MONTH)]);
        mTvYear.setText(String.valueOf(history.monthToCalendar().get(Calendar.YEAR)));
        mTvTotalIncome.setText(String.valueOf(history.monthlyIncome));
        mTvTotalExpense.setText(String.valueOf(history.calculateTotalExpense()));
        mJarAdapter.setData(history);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void setUp(View view) {
        mTvMonth = view.findViewById(R.id.tv_month);
        mTvYear = view.findViewById(R.id.tv_year);
        mTvTotalIncome = view.findViewById(R.id.tv_total_income);
        mTvTotalExpense = view.findViewById(R.id.tv_total_expenses);

        rvListJar = view.findViewById(R.id.rv_list_jar);
        rvListJar.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mJarAdapter = new JarAdapter(getContext());
        rvListJar.setAdapter(mJarAdapter);

        mPresenter.onViewPrepared();
    }
}
