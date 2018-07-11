package com.example.vuquang.jars.activity.base;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.widget.Toast;

import com.example.vuquang.jars.activity.base.BaseActivity;
import com.example.vuquang.jars.activity.utils.NetworkUtils;

/**
 * Created by VuQuang on 6/5/2018.
 */

public class ConnectivityChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(!NetworkUtils.isNetworkConnected(context)) {
            context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            Toast.makeText(context, "lost connection", Toast.LENGTH_SHORT).show();
        }
    }

}