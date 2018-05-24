package com.example.vuquang.jars.activity.expenses;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.base.BaseFragment;
import com.example.vuquang.jars.activity.base.MvpView;
import com.example.vuquang.jars.activity.data.AppDataManager;
import com.example.vuquang.jars.activity.data.db.model.JarType;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.example.vuquang.jars.activity.expenses.addexpense.AddExpenseFragment;
import com.example.vuquang.jars.activity.expenses.tabview.MainTabAdapter;
import com.example.vuquang.jars.activity.setting.SettingPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class ExpensesFragment extends BaseFragment implements MvpView {
    private final static int LIMIT = 100;

    private FloatingActionButton mFabAdd;

    ViewPager mViewPager;
    MainTabAdapter mTabAdapter;
    TabLayout mTabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = View.inflate(getActivity(), R.layout.fragment_expenses, null);
        return view;
    }


    private void startAddFragment() {
        AddExpenseFragment.show(getActivity().getSupportFragmentManager());
    }

    @Override
    protected void setUp(View view) {
        mFabAdd = view.findViewById(R.id.fab_add);
        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddFragment();
            }
        });


        mViewPager = view.findViewById(R.id.view_pager);
        mTabAdapter = new MainTabAdapter(getActivity().getSupportFragmentManager(), getContext());
        List<JarType> typeList = new ArrayList<>(Arrays.asList(JarType.values()));
        mTabAdapter.setData(typeList);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mTabAdapter);

        mTabLayout = view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        // Iterate over all tabs and set the custom view
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(mTabAdapter.getCustomTabView(i));
        }
    }
}
