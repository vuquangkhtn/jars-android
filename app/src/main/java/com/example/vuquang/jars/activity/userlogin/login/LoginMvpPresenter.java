package com.example.vuquang.jars.activity.userlogin.login;

import android.widget.EditText;

import com.example.vuquang.jars.activity.base.MvpPresenter;

/**
 * Created by VuQuang on 5/15/2018.
 */

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {
    void onSignInClicked(String email, String password);
    void onSignUpClicked();
}
