package com.example.vuquang.jars.activity.userlogin.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.base.MvpView;
import com.example.vuquang.jars.activity.data.AppDataManager;
import com.example.vuquang.jars.activity.main.MainActivity;
import com.example.vuquang.jars.activity.setting.settingdialog.SettingDialog;
import com.example.vuquang.jars.activity.userlogin.login.LoginActivity;
import com.example.vuquang.jars.activity.userlogin.NotAuthenAcitivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by VuQuang on 4/8/2018.
 */

public class SplashScreenActivity extends NotAuthenAcitivity implements SplashMvpView {

    private SplashPresenter<SplashMvpView> presenter;

    private TextView tvLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tvLogo = findViewById(R.id.tv_logo);
        tvLogo.setTypeface(JarsApp.getApp().getTypeFace("fonts/atvice.ttf"));

        presenter = new SplashPresenter<>(new AppDataManager(
                FirebaseDatabase.getInstance().getReference(),
                FirebaseAuth.getInstance()));
        presenter.onAttach(SplashScreenActivity.this);
        presenter.decideNextActivity();
    }

    @Override
    public void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        sendBroadcast(new Intent(NotAuthenAcitivity.ACTION_LOGIN));
        finish();
    }

    @Override
    public void openSettingDialog() {
        SettingDialog.show(getSupportFragmentManager());
    }
}
