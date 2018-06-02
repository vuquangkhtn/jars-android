package com.example.vuquang.jars.activity.setting.settingdialog;

import com.example.vuquang.jars.activity.base.MvpView;

/**
 * Created by VuQuang on 6/1/2018.
 */

public interface SettingDialogMvpView extends MvpView {
    void goToMain();
    void updateIncome(long monthlyIncome);
}
