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

import java.util.GregorianCalendar;

/**
 * Created by VuQuang on 5/24/2018.
 */

public class SettingPresenter<V extends SettingMvpView> extends BasePresenter<V> implements SettingMvpPresenter<V> {
    public SettingPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onSaveIncomeClicked(Long val) {
        getMvpView().showLoading();
        getDataManager().updateMonthlyIncome(JarsApp.getApp().getHistoryId(), val)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                getMvpView().hideLoading();
                if(task.isSuccessful()) {
                    getMvpView().showMessage("Update successful!");
                    getMvpView().goToMain();
                } else {
                    getMvpView().showMessage("Update failed");

                }
            }
        });

    }

    @Override
    public void loadOldIncome() {
        getDataManager().getHistoryEndPoint().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MonthlyHistory monthlyHistory = getDataManager()
                        .getMonthlyHistoryFrom(dataSnapshot, new GregorianCalendar());
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
