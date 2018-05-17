package com.example.vuquang.jars.activity.main;

import android.view.MenuItem;

import com.example.vuquang.jars.activity.base.MvpView;

/**
 * Created by VuQuang on 5/17/2018.
 */

public interface MainMvpView extends MvpView {
    void goToLoginActivity();
    void openHelpFragment();
    void openDrawer();

    void loadNavHeader(String username);
}
