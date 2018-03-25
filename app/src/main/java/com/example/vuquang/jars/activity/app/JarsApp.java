package com.example.vuquang.jars.activity.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vuquang.jars.activity.activity.WelcomeActivity;
import com.example.vuquang.jars.activity.model.Account;
import com.example.vuquang.jars.activity.utils.SharePrefHelper;

/**
 * Created by VuQuang on 3/24/2018.
 */

public class JarsApp extends Application{
    private static JarsApp instance;

//    private RequestQueue mRequestQueue;
//    private Cache mCache;

    private Account mAccount;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

//        this.mCache = new DiskBasedCache(getCacheDir("main"), (getMainDiskCacheSizeInMb() * 1024) * 1024);
//        this.mRequestQueue = new RequestQueue(this.mCache, createNetwork(), 20);
//        this.mRequestQueue.start();

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

    public Typeface getTypeFaceLogo() {
        return Typeface.createFromAsset(getAssets(),  "fonts/atvice.ttf");
    }

}
