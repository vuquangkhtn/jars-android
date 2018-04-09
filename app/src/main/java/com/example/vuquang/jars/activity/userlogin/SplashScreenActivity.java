package com.example.vuquang.jars.activity.userlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.main.MainActivity;
import com.example.vuquang.jars.activity.userlogin.model.AccessMode;
import com.example.vuquang.jars.activity.utils.Pref;
import com.example.vuquang.jars.activity.utils.SharePrefHelper;

/**
 * Created by VuQuang on 4/8/2018.
 */

public class SplashScreenActivity extends NotAuthenAcitivity {
    private TextView tvLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tvLogo = findViewById(R.id.tv_logo);
        tvLogo.setTypeface(JarsApp.getApp().getTypeFace("fonts/atvice.ttf"));

        processLogin();
    }

    private void processLogin() {
        String accessMode = SharePrefHelper.get().getString(Pref.access_pref);
        if(accessMode.equals(AccessMode.OFFLINE.getMode())) {
            goToMain(AccessMode.OFFLINE);
        } else {
            String username = SharePrefHelper.get().getString(Pref.username_pref);
            String pass = SharePrefHelper.get().getString(Pref.password_pref);
            if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pass)) {
                goToMain(AccessMode.ONLINE);
            } else {
                goToWelcomeActivity();
            }
        }
    }

    private void goToWelcomeActivity() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void requestAccessMode(AccessMode mode) {
        SharePrefHelper.get().setString("access_pref", mode.getMode());
    }

    private void goToMain(AccessMode mode) {
        requestAccessMode(mode);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        sendBroadcast(new Intent(NotAuthenAcitivity.ACTION_LOGIN));
        finish();
    }
}
