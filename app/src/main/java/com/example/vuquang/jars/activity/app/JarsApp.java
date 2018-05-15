package com.example.vuquang.jars.activity.app;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;

import com.example.vuquang.jars.activity.userlogin.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by VuQuang on 4/8/2018.
 */

public class JarsApp extends Application{
    public static final String ACTION_LOGOUT = "ACTION_LOGOUT";
    private FirebaseAuth mAuth;
    private static JarsApp instance;

    private long totalIncome = 10000;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mAuth = FirebaseAuth.getInstance();
    }

    public static JarsApp getApp() {
        return instance;
    }

    public void logout(final Activity currentActivity) {
        mAuth.signOut();
        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    Intent i = new Intent(currentActivity, LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    Intent logoutIntent = new Intent(ACTION_LOGOUT);
                    sendBroadcast(logoutIntent);
                }
            }
        };
        mAuth.addAuthStateListener(authListener);


    }

    public Typeface getTypeFace(String path) {
        return Typeface.createFromAsset(getAssets(),  path);
    }

    public long getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(long totalIncome) {
        this.totalIncome = totalIncome;
    }
}
