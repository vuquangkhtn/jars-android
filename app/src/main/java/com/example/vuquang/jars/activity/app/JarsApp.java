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
    private static JarsApp instance;

    private long totalIncome = 10000;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static JarsApp getApp() {
        return instance;
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
