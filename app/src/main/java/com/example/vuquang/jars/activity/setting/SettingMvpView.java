package com.example.vuquang.jars.activity.setting;

import com.example.vuquang.jars.activity.base.MvpView;

/**
 * Created by VuQuang on 5/24/2018.
 */

public interface SettingMvpView extends MvpView {

    void updateIncome(long monthlyIncome);

    void goToMain();
}
