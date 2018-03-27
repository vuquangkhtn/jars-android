package com.example.vuquang.jars.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.fragments.HelpFragment;
import com.example.vuquang.jars.activity.utils.SharePrefHelper;


public class MainActivity extends AppCompatActivity {

    private View navHeader;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ImageButton imbMenu, imbBack, imbHelp;
    private TextView txtTitle, txtName;

    ViewPager mViewPager;
    MainTabAdapter mTabAdapter;
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = findViewById(R.id.tv_title);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navHeader = navigationView.getHeaderView(0);
        txtName = navHeader.findViewById(R.id.name);

        imbBack = findViewById(R.id.imv_navi_back);
        imbBack.setVisibility(View.GONE);

        imbMenu = findViewById(R.id.imv_navi_menu);
        imbMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer();
            }
        });

        imbHelp = findViewById(R.id.imv_navi_help);
        imbHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHelpFragmennt();
            }
        });

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabAdapter = new MainTabAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mTabAdapter);
        mViewPager.setOffscreenPageLimit(2);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
//        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
////                txtTitle.setText(tab.getText());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        // Iterate over all tabs and set the custom view
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(mTabAdapter.getCustomTabView(i));
        }

        loadNavHeader();

        setUpNavigationView();
    }

    private void openHelpFragmennt() {
        HelpFragment.show(getSupportFragmentManager());
    }


    public void openDrawer() {
        drawer.openDrawer(navigationView);
    }

    public void closeDrawer(){
        drawer.closeDrawer(navigationView);
    }

    private void loadNavHeader() {
        // name, website
        txtTitle.setText("JARS");
        txtName.setText(SharePrefHelper.get().getString("username_pref"));

    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_sign_out:
                        JarsApp.getApp().logout(MainActivity.this);
                        finish();
                        return true;
                }
                return true;
            }
        });
        drawer.closeDrawers();
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, navigationView);
    }


}
