package com.example.vuquang.jars.activity.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.app.JarsApp;


public class MainActivity extends AppCompatActivity {

    private View navHeader;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ImageButton imbMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navHeader = navigationView.getHeaderView(0);

        imbMenu = findViewById(R.id.imv_navi_menu);
        imbMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer();
            }
        });


        loadNavHeader();

        setUpNavigationView();
    }


    public void openDrawer() {
        drawer.openDrawer(navigationView);
    }

    public void closeDrawer(){
        drawer.closeDrawer(navigationView);
    }

    private void loadNavHeader() {
        // name, website
//        txtName.setText(SharePrefHelper.get().getString("username_pref"));
    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_book:
                        closeDrawer();
                        return true;
                    case R.id.nav_author:
                        closeDrawer();
                        return true;
                    case R.id.nav_favor:
                        closeDrawer();
                        return true;
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
