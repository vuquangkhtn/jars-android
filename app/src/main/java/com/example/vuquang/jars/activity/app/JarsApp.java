package com.example.vuquang.jars.activity.app;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Typeface;

import com.example.vuquang.jars.activity.userlogin.WelcomeActivity;
import com.example.vuquang.jars.activity.utils.SharePrefHelper;

/**
 * Created by VuQuang on 4/8/2018.
 */

public class JarsApp extends Application{
    public static final String ACTION_LOGOUT = "ACTION_LOGOUT";

    private static JarsApp instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

//        mAccount = new Account();

    }

    public static JarsApp getApp() {
        return instance;
    }

    public void logout(Activity currentActivity) {
        SharePrefHelper.get().setString("username_pref", "");
        SharePrefHelper.get().setString("password_pref", "");
        Intent i = new Intent(currentActivity, WelcomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

        Intent logoutIntent = new Intent(ACTION_LOGOUT);
        sendBroadcast(logoutIntent);
    }

//    public Account getAccount(){
//        return mAccount;
//    }
//
    public Typeface getTypeFace(String path) {
        return Typeface.createFromAsset(getAssets(),  path);
    }

}
