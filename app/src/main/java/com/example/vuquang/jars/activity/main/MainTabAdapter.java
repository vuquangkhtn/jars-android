package com.example.vuquang.jars.activity.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.realitytab.RealityFragment;
import com.example.vuquang.jars.activity.theorytab.TheoryFragment;

/**
 * Created by CPU10584-local on 17-Jan-18.
 */

public class MainTabAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private FragmentType[] listTabs;

    public MainTabAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        listTabs = FragmentType.values();
    }

    @Override
    public Fragment getItem(int position) {
        FragmentType type = listTabs[position];
        switch (type) {
            case THEORY_JAR_FRAGMENT_POSITION:
                return new TheoryFragment();
            case REALITY_JAR_FRAGMENT_POSITION:
                return new RealityFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return listTabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTabs[position].getTitle();
    }

    public View getCustomTabView(int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_tab_item, null);
        ImageView imvIcon = (ImageView) v.findViewById(R.id.imv_tab_icon);
        imvIcon.setImageResource(listTabs[position].resIcon);
        TextView tvName = (TextView) v.findViewById(R.id.tv_tab_name);
        tvName.setText(listTabs[position].title);
        return v;
    }

    enum FragmentType {
        REALITY_JAR_FRAGMENT_POSITION("Reality Jar", R.drawable.tab_ic_reality),
        THEORY_JAR_FRAGMENT_POSITION("Theory Jar", R.drawable.tab_ic_theory);

        private String title;

        private int resIcon;

        FragmentType(String title, int resIcon) {
            this.title = title;
            this.resIcon = resIcon;
        }

        public String getTitle() {
            return title;
        }
    }
}