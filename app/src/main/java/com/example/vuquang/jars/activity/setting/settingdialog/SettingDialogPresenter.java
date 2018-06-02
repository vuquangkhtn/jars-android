package com.example.vuquang.jars.activity.setting.settingdialog;

import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.base.BasePresenter;
import com.example.vuquang.jars.activity.data.DataManager;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.GregorianCalendar;

/**
 * Created by VuQuang on 6/1/2018.
 */

public class SettingDialogPresenter<V extends SettingDialogMvpView>
        extends BasePresenter<V> implements SettingDialogMvpPresenter<V> {
    public SettingDialogPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onBtnSaveClicked(long monthlyIncome) {
        String hisId = getDataManager().createHistory(monthlyIncome);
        JarsApp.getApp().setHistoryId(hisId);
        getMvpView().goToMain();
    }

    @Override
    public void loadOldIncome() {
        getDataManager().getHistoryEndPoint().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MonthlyHistory monthlyHistory = getDataManager()
                        .getNearestHistory(dataSnapshot);
                if(monthlyHistory != null) {
                    getMvpView().updateIncome(monthlyHistory.monthlyIncome);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
