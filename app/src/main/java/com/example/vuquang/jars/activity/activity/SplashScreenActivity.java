package com.example.vuquang.jars.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.utils.SharePrefHelper;

/**
 * Created by chuongnguyen on 4/21/17.
 */

public class SplashScreenActivity extends NotAuthenAcitivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        processLogin();
    }

    private void processLogin() {
        String username = SharePrefHelper.get().getString("username_pref");
        String pass = SharePrefHelper.get().getString("password_pref");
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pass)) {
            goToMain();
        } else {
            goToWelcomeActivity();
        }

    }


    private void goToWelcomeActivity() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        sendBroadcast(new Intent(NotAuthenAcitivity.ACTION_LOGIN));
        finish();
    }
}
