package com.example.vuquang.jars.activity.app;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Typeface;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.activity.WelcomeActivity;
import com.example.vuquang.jars.activity.model.Account;
import com.example.vuquang.jars.activity.model.Jar;
import com.example.vuquang.jars.activity.utils.SharePrefHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VuQuang on 3/24/2018.
 */

public class JarsApp extends Application{
    private static JarsApp instance;
    private Account mAccount;
    public long totalAmount;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        mAccount = new Account();

    }

    public Account getAccount(){
        return mAccount;
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

        Intent logoutIntent = new Intent("ACTION_LOGOUT");
        sendBroadcast(logoutIntent);
    }

    public Typeface getTypeFace(String path) {
        return Typeface.createFromAsset(getAssets(),  path);
    }

    public List<Jar> initListJar(long total) {
        List<Jar> listJar = new ArrayList<>();
        Jar.JarType[] jarTypes = Jar.JarType.values();
        for (Jar.JarType type:jarTypes) {
            listJar.add(Jar.initJar(type, total));
        }
        return listJar;
    }

}
