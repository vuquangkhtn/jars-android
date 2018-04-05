package com.example.vuquang.jars.activity.expenses;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.expenses.ExpensesFragment;
import com.example.vuquang.jars.activity.model.Expense;
import com.example.vuquang.jars.activity.model.Jar;
import com.example.vuquang.jars.activity.model.RealityJar;
import com.example.vuquang.jars.activity.statistics.StatisticsFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by CPU10584-local on 17-Jan-18.
 */

public class MainTabAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<RealityJar> listTabs;

    public MainTabAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
//        listTabs = JarsApp.getApp().initListJar(1000);
    }

    @Override
    public Fragment getItem(int position) {
        FilterFragment fragment = new FilterFragment();
        List<Expense> list = new ArrayList<>();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("Expenses", (ArrayList<? extends Parcelable>) list);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return listTabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Expenses";
    }

    public View getCustomTabView(int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_tab_item, null);
        ImageView imvIcon = (ImageView) v.findViewById(R.id.imv_tab_icon);
//        imvIcon.setImageResource(listTabs[position].resIcon);
        TextView tvName = (TextView) v.findViewById(R.id.tv_tab_name);
        tvName.setText(getPageTitle(position));
        return v;
    }
}