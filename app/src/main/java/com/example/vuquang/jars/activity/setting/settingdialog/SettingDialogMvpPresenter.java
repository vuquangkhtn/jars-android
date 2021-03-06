package com.example.vuquang.jars.activity.setting.settingdialog;

import com.example.vuquang.jars.activity.base.MvpPresenter;
import com.example.vuquang.jars.activity.base.MvpView;

/**
 * Created by VuQuang on 6/1/2018.
 */

public interface SettingDialogMvpPresenter<V extends MvpView> extends MvpPresenter<V> {
    void onBtnSaveClicked(long monthlyIncome);

    void loadOldIncome();
}
