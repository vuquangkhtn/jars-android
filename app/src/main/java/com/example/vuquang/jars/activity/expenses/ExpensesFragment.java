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
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class ExpensesFragment extends Fragment {
    private final static int LIMIT = 100;

    private FloatingActionButton mFabAdd;

    ViewPager mViewPager;
    MainTabAdapter mTabAdapter;
    TabLayout mTabLayout;
    MonthlyHistory history;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = View.inflate(getActivity(), R.layout.fragment_expenses, null);

        mFabAdd = view.findViewById(R.id.fab_add);
        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddFragment();
            }
        });

        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        // Iterate over all tabs and set the custom view
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(mTabAdapter.getCustomTabView(i));
        }

        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mTabAdapter = new MainTabAdapter(getActivity().getSupportFragmentManager(), getContext());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mTabAdapter);

        //Todo: load expense list

        return view;
    }


    private void startAddFragment() {
        AddExpenseFragment.show(getActivity().getSupportFragmentManager());
    }
}
