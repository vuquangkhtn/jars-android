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
import com.example.vuquang.jars.activity.model.Jar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class MainTabAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Jar> jarList;

    public MainTabAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        jarList = new ArrayList<>();
    }

    public void setData(List<Jar> jarList) {
        this.jarList = jarList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new FilterExpenseFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(FilterExpenseFragment.ARG_OBJECT, (ArrayList<? extends Parcelable>) jarList.get(position).expenseList);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return jarList.size();
    }

    public View getCustomTabView(int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_tab_item, null);
        ImageView imvIcon = (ImageView) v.findViewById(R.id.imv_tab_icon);
        imvIcon.setImageResource(jarList.get(position).getResIcon());
        TextView tvTabName = (TextView) v.findViewById(R.id.tv_tab_name);
        tvTabName.setText(jarList.get(position).getName());
        return v;
    }
}