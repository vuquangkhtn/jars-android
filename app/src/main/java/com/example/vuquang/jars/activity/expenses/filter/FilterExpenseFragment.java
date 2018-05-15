package com.example.vuquang.jars.activity.expenses.filter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuquang.jars.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class FilterExpenseFragment extends Fragment {
    public static final String ARG_OBJECT = "Expenses";

    ExpensesAdapter mAdapter;
    RecyclerView rvExpense;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_filter_expense, container, false);
        Bundle args = getArguments();
        int type = args.getInt(ARG_OBJECT);
        rvExpense = rootView.findViewById(R.id.rv_list_expense);
        rvExpense.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new ExpensesAdapter(getContext());
        rvExpense.setAdapter(mAdapter);

        loadExpenseListBy(type);
        return rootView;
    }

    public void loadExpenseListBy(int type) {
        //Todo: load expense list by type
    }
}
