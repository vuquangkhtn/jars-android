package com.example.vuquang.jars.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.expenses.ExpensesFragment;
import com.example.vuquang.jars.activity.fragments.HelpDialogFragment;
import com.example.vuquang.jars.activity.statistics.StatisticsFragment;
import com.example.vuquang.jars.activity.utils.SharePrefHelper;


public class MainActivity extends AppCompatActivity {

    private View navHeader;
    private DrawerLayout mDrawer;
    private NavigationView navigationView;
    private ImageButton imbMenu, imbBack, imbHelp;
    private TextView txtTitle, txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = findViewById(R.id.tv_title);
        mDrawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);

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

        loadNavHeader();
    }

    private void openHelpFragmennt() {
        HelpDialogFragment.show(getSupportFragmentManager());
    }


    public void openDrawer() {
        mDrawer.openDrawer(navigationView);
    }

    public void closeDrawer(){
        mDrawer.closeDrawer(navigationView);
    }

    private void loadNavHeader() {
        // name, website
        txtTitle.setText("JARS");
        txtName.setText(SharePrefHelper.get().getString("username_pref"));

    }
//
//    private void setUpNavigationView() {
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.nav_sign_out:
//                        JarsApp.getApp().logout(MainActivity.this);
//                        finish();
//                        return true;
//                }
//                    return true;
//            }
//        });
//        mDrawer.closeDrawers();
//        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, navigationView);
//    }


    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_expenses:
                fragmentClass = ExpensesFragment.class;
                break;
            case R.id.nav_statistics:
                fragmentClass = StatisticsFragment.class;
                break;
            case R.id.nav_sign_out:
                JarsApp.getApp().logout(MainActivity.this);
                return;
            default:
                fragmentClass = ExpensesFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation mDrawer
        mDrawer.closeDrawers();
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, navigationView);
    }
}
