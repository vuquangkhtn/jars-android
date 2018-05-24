package com.example.vuquang.jars.activity.userlogin.signup;

import com.example.vuquang.jars.activity.base.MvpPresenter;

/**
 * Created by VuQuang on 5/24/2018.
 */

public interface SignUpMvpPresenter<V extends SignUpMvpView> extends MvpPresenter<V> {
    void onCreateAccClicked(String email, String password);

    void onNaviBackClicked();

    void checkAllEditTextFilled();
}
