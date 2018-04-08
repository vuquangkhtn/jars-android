package com.example.vuquang.jars.activity.userlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.userlogin.model.AccessMode;
import com.example.vuquang.jars.activity.utils.SharePrefHelper;

/**
 * Created by VuQuang on 4/8/2018.
 */

public class WelcomeActivity extends AppCompatActivity {

    private Button mBtnOffline, mBtnOnline;
    private TextView tvLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvLogo = findViewById(R.id.tv_logo);
        tvLogo.setTypeface(JarsApp.getApp().getTypeFace("fonts/atvice.ttf"));

        mBtnOffline = (Button) findViewById(R.id.btn_offline_mode);
        mBtnOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestAccessMode(AccessMode.OFFLINE);
                goToMain(AccessMode.OFFLINE);
            }
        });

        mBtnOnline = (Button) findViewById(R.id.btn_online_mode);
        mBtnOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestAccessMode(AccessMode.ONLINE);
                goToLogin();
            }
        });
    }

    private void requestAccessMode(AccessMode mode) {
        SharePrefHelper.get().setString("access_pref", mode.getMode());
    }

    private void goToLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        finish();
    }

    private void goToMain(AccessMode mode) {
//        Intent i = new Intent(this, MainActivity.class);
//        startActivity(i);
//        sendBroadcast(new Intent(NotAuthenAcitivity.ACTION_LOGIN));
//        finish();
    }

}

