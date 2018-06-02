package com.example.vuquang.jars.activity.expenses.showexpense;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.base.BaseFragment;
import com.example.vuquang.jars.activity.data.AppDataManager;
import com.example.vuquang.jars.activity.data.db.model.Expense;
import com.example.vuquang.jars.activity.data.db.model.JarType;
import com.example.vuquang.jars.activity.expenses.LayoutSwitcher;
import com.example.vuquang.jars.activity.expenses.adapter.ExpensesAdapter;
import com.example.vuquang.jars.activity.expenses.addexpense.AddExpenseFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class ExpensesFragment extends BaseFragment implements ExpenseMvpView {
    private FloatingActionButton mFabAdd;
    ExpensesAdapter mAdapter;
    RecyclerView rvExpense;
    TabLayout mTabLayout;
    AddExpenseFragment addExpenseFragment;
    LayoutSwitcher layoutSwitcher;

    private ExpensePresenter<ExpenseMvpView> mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = View.inflate(getActivity(), R.layout.fragment_expenses, null);
//        View viewContainer = view.findViewById(R.id.layout_container_rv);
        mPresenter = new ExpensePresenter<>(new AppDataManager(
                FirebaseDatabase.getInstance().getReference(),
                FirebaseAuth.getInstance()
        ));

        mPresenter.onAttach(ExpensesFragment.this);
        return view;
    }


    private void startAddFragment() {
        addExpenseFragment.show(getActivity().getSupportFragmentManager(),"AddExpenseFragment");
    }

    @Override
    protected void setUp(View view) {
        addExpenseFragment = AddExpenseFragment.newInstance();
        addExpenseFragment.setDialogListener(new AddExpenseFragment.DialogListener() {
            @Override
            public void onDismissDialog(DialogInterface dialog) {
                mPresenter.onDismissAddExpenseDialog();
            }
        });
        layoutSwitcher = new LayoutSwitcher(view, R.id.rv_list_expense, R.id.layout_empty, new LayoutSwitcher.RetryButtonListener() {
            @Override
            public void onRetry() {
                mPresenter.loadExpenseList();
            }
        });
        mFabAdd = view.findViewById(R.id.fab_add);
        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddFragment();
            }
        });

        List<JarType> typeList = new ArrayList<>(Arrays.asList(JarType.values()));

        mTabLayout = view.findViewById(R.id.tab_layout);
        for (int i = 0; i < typeList.size(); i++) {
            TabLayout.Tab tab = mTabLayout.newTab().setText(typeList.get(i).getName());
            mTabLayout.addTab(tab);
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPresenter.onTabSelected(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        rvExpense = view.findViewById(R.id.rv_list_expense);
        rvExpense.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new ExpensesAdapter(getContext());
        rvExpense.setAdapter(mAdapter);

        mPresenter.loadExpenseList();

    }

    @Override
    public void setExpenseListBy(List<Expense> list) {
        mAdapter.setData(list);
    }

    @Override
    public void setDefaultTab() {
        mTabLayout.setSelected(false);
        mTabLayout.getTabAt(JarType.ALL.getId()).select();
    }

    @Override
    public void switchToEmptyMode() {
        layoutSwitcher.switchToEmptyMode();
    }

    @Override
    public void switchToDataMode() {
        layoutSwitcher.switchToDataMode();
    }
}
