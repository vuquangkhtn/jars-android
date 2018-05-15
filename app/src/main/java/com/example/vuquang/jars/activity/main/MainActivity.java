package com.example.vuquang.jars.activity.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.base.BaseActivity;
import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.expenses.ExpensesFragment;
import com.example.vuquang.jars.activity.statistics.StatisticsFragment;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class MainActivity extends BaseActivity {
    private View navHeader;
    private LinearLayout layoutOffline, layoutUser;
    private DrawerLayout mDrawer;
    private NavigationView navigationView;
    private ImageButton imbMenu, imbBack, imbHelp;
    private TextView txtTitle, txtName;
    private ImageView mImvProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = findViewById(R.id.tv_title);
        mDrawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);

        navHeader = navigationView.getHeaderView(0);
        layoutOffline = navHeader.findViewById(R.id.layout_offline);
        layoutUser = navHeader.findViewById(R.id.layout_user);
        txtName = navHeader.findViewById(R.id.name);
        mImvProfile = navHeader.findViewById(R.id.img_profile);

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

        loadNavHeader();
        setDefaultFragment(ExpensesFragment.class);

    }

    private void setDefaultFragment(Class fragmentClass) {
        Fragment defaultFragment = null;
        if(fragmentClass == ExpensesFragment.class) {
            txtTitle.setText(getResources().getString(R.string.expenses_name));
        } else {
            txtTitle.setText(getResources().getString(R.string.statistics_name));
        }
        try {
            defaultFragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // set default fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, defaultFragment).commit();
    }

    private void openHelpFragmennt() {
        HelpDialogFragment.show(this.getSupportFragmentManager());
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
        setHeaderBy();
//        txtName.setText(SharePrefHelper.get().getString(SharePref.username_pref));
    }

    private void setHeaderBy() {
        layoutUser.setVisibility(View.VISIBLE);
        layoutOffline.setVisibility(View.GONE);
        mImvProfile.setImageResource(R.drawable.ic_user_test);
    }

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
                finish();
                return;
            default:
                fragmentClass = ExpensesFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(fragmentClass == ExpensesFragment.class) {
            txtTitle.setText(getResources().getString(R.string.expenses_name));
        } else {
            txtTitle.setText(getResources().getString(R.string.statistics_name));
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
