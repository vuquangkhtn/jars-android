package com.example.vuquang.jars.activity.main;

import android.content.Intent;
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
import com.example.vuquang.jars.activity.data.AppDataManager;
import com.example.vuquang.jars.activity.expenses.ExpensesFragment;
import com.example.vuquang.jars.activity.setting.SettingsFragment;
import com.example.vuquang.jars.activity.statistics.StatisticsFragment;
import com.example.vuquang.jars.activity.userlogin.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class MainActivity extends BaseActivity implements MainMvpView {
    private View navHeader;
    private LinearLayout  layoutUser;
    private DrawerLayout mDrawer;
    private NavigationView navigationView;
    private ImageButton imbMenu, imbBack, imbHelp;
    private TextView txtTitle, txtName;
    private ImageView mImvProfile;

    private MainMvpPresenter<MainMvpView> mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter<>(new AppDataManager(
                FirebaseDatabase.getInstance().getReference(),
                FirebaseAuth.getInstance()
        ));

        mPresenter.onAttach(MainActivity.this);

        txtTitle = findViewById(R.id.tv_title);
        mDrawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch(menuItem.getItemId()) {
                            case R.id.nav_expenses:
                                changeFragment(new ExpensesFragment(), menuItem);
                                break;
                            case R.id.nav_statistics:
                                changeFragment(new StatisticsFragment(), menuItem);
                                break;
                            case R.id.nav_setting:
                                changeFragment(new SettingsFragment(), menuItem);
                                break;
                            case R.id.nav_sign_out:
                                mPresenter.onLogoutClicked();
                                finish();
                        }
                        return true;
                    }
                });

        navHeader = navigationView.getHeaderView(0);
        layoutUser = navHeader.findViewById(R.id.layout_user);
        txtName = navHeader.findViewById(R.id.tv_username);
        mImvProfile = navHeader.findViewById(R.id.img_profile);

        imbBack = findViewById(R.id.imv_navi_back);
        imbBack.setVisibility(View.GONE);

        imbMenu = findViewById(R.id.imv_navi_menu);
        imbMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onMenuClicked();
            }
        });

        imbHelp = findViewById(R.id.imv_navi_help);
        imbHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onHelpClicked();
            }
        });

        setDefaultFragment();

        mPresenter.onNavHeaderPrepared();

    }

    private void setDefaultFragment() {
        txtTitle.setText(getResources().getString(R.string.expenses_name));
        // set default fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new ExpensesFragment()).commit();
    }

    @Override
    public void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void openHelpFragment() {
        HelpDialogFragment.show(this.getSupportFragmentManager());
    }

    @Override
    public void openDrawer() {
        mDrawer.openDrawer(navigationView);
    }

    @Override
    public void loadNavHeader(String username) {
        layoutUser.setVisibility(View.VISIBLE);
        mImvProfile.setImageResource(R.drawable.ic_user_test);
        txtName.setText(username);
    }

    private void changeFragment(Fragment fragment, MenuItem menuItem) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        txtTitle.setText(menuItem.getTitle());
        // Close the navigation mDrawer
        mDrawer.closeDrawers();
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, navigationView);
    }
}
