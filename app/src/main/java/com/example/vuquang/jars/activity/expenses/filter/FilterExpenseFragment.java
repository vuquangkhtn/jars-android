package com.example.vuquang.jars.activity.expenses.filter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.base.BaseFragment;
import com.example.vuquang.jars.activity.base.MvpView;
import com.example.vuquang.jars.activity.data.AppDataManager;
import com.example.vuquang.jars.activity.data.db.model.JarType;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.example.vuquang.jars.activity.expenses.filter.adapter.ExpensesAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */

public class FilterExpenseFragment extends BaseFragment implements MvpView {
    public static final String ARG_OBJECT = "Expenses";

    ExpensesAdapter mAdapter;
    RecyclerView rvExpense;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View view = inflater.inflate(R.layout.fragment_filter_expense, container, false);
        return view;
    }

    @Override
    protected void setUp(View view) {
        Bundle args = getArguments();
        int type = args.getInt(ARG_OBJECT);
        rvExpense = view.findViewById(R.id.rv_list_expense);
        rvExpense.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new ExpensesAdapter(getContext());
        rvExpense.setAdapter(mAdapter);
        MonthlyHistory history = JarsApp.getApp().getMonthlyHistory();
        mAdapter.setData(history.findExpensesListBy(JarType.values()[type]));
    }
}
