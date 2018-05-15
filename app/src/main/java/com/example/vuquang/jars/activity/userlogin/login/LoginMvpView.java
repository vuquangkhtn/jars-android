package com.example.vuquang.jars.activity.userlogin.login;

import com.example.vuquang.jars.activity.base.MvpView;

/**
 * Created by VuQuang on 5/15/2018.
 */

public interface LoginMvpView extends MvpView {
    void goToMain();
    void goToSignUpAct();
    void showSnackBar(String message);
}
