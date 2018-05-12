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
import com.example.vuquang.jars.activity.expenses.filter.FilterExpenseFragment;
import com.example.vuquang.jars.activity.model.Expense;
import com.example.vuquang.jars.activity.model.JarType;
import com.example.vuquang.jars.activity.model.MonthlyHistory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class MainTabAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private MonthlyHistory mHistory;

    public MainTabAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    public void setData(MonthlyHistory history) {
        this.mHistory = history;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new FilterExpenseFragment();
        List<Expense> list = mHistory.getExpensesListBy(JarType.values()[position]);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(FilterExpenseFragment.ARG_OBJECT, (ArrayList<? extends Parcelable>) list);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return JarType.values().length;
    }

    public View getCustomTabView(int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_tab_item, null);
        JarType type = JarType.values()[position];
        ImageView imvIcon = (ImageView) v.findViewById(R.id.imv_tab_icon);
        imvIcon.setImageResource(type.getResIdIcon());
        TextView tvTabName = (TextView) v.findViewById(R.id.tv_tab_name);
        tvTabName.setText(type.getName());
        return v;
    }
}