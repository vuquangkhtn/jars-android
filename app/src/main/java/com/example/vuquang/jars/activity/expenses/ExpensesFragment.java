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

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpensesFragment extends Fragment {

    private FloatingActionButton fabAdd;

    ViewPager mViewPager;
    MainTabAdapter mTabAdapter;
    TabLayout mTabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = View.inflate(getActivity(), R.layout.fragment_expenses, null);
        fabAdd = view.findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddFragment();
            }
        });



        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mTabAdapter = new MainTabAdapter(getActivity().getSupportFragmentManager(), getContext());
        mViewPager.setAdapter(mTabAdapter);
        mViewPager.setOffscreenPageLimit(2);

        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        // Iterate over all tabs and set the custom view
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(mTabAdapter.getCustomTabView(i));
        }
        return view;
    }

    private void startAddFragment() {
        AddExpenseFragment.show(getActivity().getSupportFragmentManager());
    }

}
