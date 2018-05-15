package com.example.vuquang.jars.activity.statistics;

import com.example.vuquang.jars.activity.base.BasePresenter;
import com.example.vuquang.jars.activity.data.DataManager;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by VuQuang on 5/14/2018.
 */

public class StatisticsPresenter<V extends StatisticsMvpView> extends BasePresenter<V> implements StatisticsMvpPresenter<V> {

    public StatisticsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();
        getDataManager().getHistoryEndPoint().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getMvpView().hideLoading();
                MonthlyHistory history = getDataManager().getMonthlyHistoryFrom(dataSnapshot);
                getMvpView().updateUI(history);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                getMvpView().hideLoading();
                getMvpView().showToast(databaseError.getMessage());

            }
        });
    }
}
