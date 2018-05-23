package com.example.vuquang.jars.activity.expenses.tabview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.expenses.filter.FilterExpenseFragment;
import com.example.vuquang.jars.activity.data.db.model.JarType;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class MainTabAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public MainTabAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new FilterExpenseFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(FilterExpenseFragment.ARG_OBJECT, position);
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