package com.example.vuquang.jars.activity.userlogin.signup;

import com.example.vuquang.jars.activity.base.MvpView;

/**
 * Created by VuQuang on 5/24/2018.
 */

public interface SignUpMvpView extends MvpView{
    void checkAllEdtFilled();
    void goToLogin();
    boolean isValid();
    boolean validateForm();
}
