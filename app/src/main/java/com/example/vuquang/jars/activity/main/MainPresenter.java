package com.example.vuquang.jars.activity.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.example.vuquang.jars.activity.base.BasePresenter;
import com.example.vuquang.jars.activity.data.DataManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by VuQuang on 5/17/2018.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {
    public MainPresenter(DataManager dataManager) {
        super(dataManager);


    }

    @Override
    public void onLogoutClicked() {
        FirebaseAuth auth = getDataManager().signOut();
        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    getMvpView().goToLoginActivity();
                }
            }
        });

    }

    @Override
    public void onMenuClicked() {
        getMvpView().openDrawer();
    }

    @Override
    public void onHelpClicked() {
        getMvpView().openHelpFragment();
    }

    @Override
    public void onNavHeaderPrepared() {
        getMvpView().loadNavHeader(getDataManager().getUsername());
    }
}
