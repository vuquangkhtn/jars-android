package com.example.vuquang.jars.activity.statistics;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.base.BaseFragment;
import com.example.vuquang.jars.activity.data.AppDataManager;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.example.vuquang.jars.activity.statistics.adapter.JarAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class StatisticsFragment extends BaseFragment implements StatisticsMvpView{
    private RecyclerView rvListJar;
    private JarAdapter mJarAdapter;
    private TextView mTvTotalIncome, mTvTotalExpense;
    private TextView mTvMonth, mTvYear;
    private FrameLayout mLayoutContainer;

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
    public void showDatePickerDialog(GregorianCalendar calendar) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                AlertDialog.THEME_DEVICE_DEFAULT_DARK,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        mPresenter.showHistoryBy(newDate);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE)){
            @Override
            protected void onCreate(Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                int day = getContext().getResources().getIdentifier("android:id/day", null, null);
                if(day != 0){
                    View dayPicker = findViewById(day);
                    if(dayPicker != null){
                        //Set Day view visibility Off/Gone
                        dayPicker.setVisibility(View.GONE);
                    }
                }
            }
        };
        datePickerDialog.show();
    }

    @Override
    protected void setUp(View view) {
        mTvMonth = view.findViewById(R.id.tv_month);
        mTvYear = view.findViewById(R.id.tv_year);
        mTvTotalIncome = view.findViewById(R.id.tv_total_income);
        mTvTotalExpense = view.findViewById(R.id.tv_total_expenses);

        mLayoutContainer = view.findViewById(R.id.layout_info_container);
        mLayoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.showDatePickerDialog();
            }
        });

        rvListJar = view.findViewById(R.id.rv_list_jar);
        rvListJar.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mJarAdapter = new JarAdapter(getContext());
        rvListJar.setAdapter(mJarAdapter);

        mPresenter.onViewPrepared();
    }

}
