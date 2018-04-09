package com.example.vuquang.jars.activity.expenses;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.model.Jar;

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
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
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
