package com.example.vuquang.jars.activity.main;

import android.view.MenuItem;

import com.example.vuquang.jars.activity.base.MvpPresenter;

/**
 * Created by VuQuang on 5/17/2018.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
    void onLogoutClicked();
    void onMenuClicked();
    void onHelpClicked();
    void onNavHeaderPrepared();

}
