package com.example.vuquang.jars.activity.setting;

import com.example.vuquang.jars.activity.base.MvpPresenter;

/**
 * Created by VuQuang on 5/24/2018.
 */

public interface SettingMvpPresenter<V extends SettingMvpView> extends MvpPresenter<V> {
    void onSaveIncomeClicked(Long val);
}
