package com.example.vuquang.jars.activity.setting;

import android.support.annotation.NonNull;

import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.base.BasePresenter;
import com.example.vuquang.jars.activity.data.DataManager;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by VuQuang on 5/24/2018.
 */

public class SettingPresenter<V extends SettingMvpView> extends BasePresenter<V> implements SettingMvpPresenter<V> {
    public SettingPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onSaveIncomeClicked(Long val) {
        JarsApp.getApp().getMonthlyHistory().monthlyIncome = val;
        getMvpView().showLoading();
        getDataManager().updateHistory(JarsApp.getApp().getMonthlyHistory()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                getMvpView().hideLoading();
                getMvpView().showMessage("Update successful!");
            }
        });

    }
}
