package com.example.vuquang.jars.activity.statistics;

import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.base.BasePresenter;
import com.example.vuquang.jars.activity.data.DataManager;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by VuQuang on 5/14/2018.
 */

public class StatisticsPresenter<V extends StatisticsMvpView> extends BasePresenter<V> implements StatisticsMvpPresenter<V> {

    private DataSnapshot data;
    private MonthlyHistory monthlyHistory;

    public StatisticsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();
        getDataManager().getHistoryEndPoint().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getMvpView().hideLoading();
                data = dataSnapshot;
                monthlyHistory = getDataManager()
                        .getMonthlyHistoryFrom(dataSnapshot, new GregorianCalendar());
                if(monthlyHistory != null) {
                    getMvpView().updateUI(monthlyHistory);
                } else {
                    getMvpView().showMessage("Load data failed");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void showDatePickerDialog() {
        if(monthlyHistory != null) {
            getMvpView().showDatePickerDialog(monthlyHistory.monthToCalendar());

        } else {

            getMvpView().showDatePickerDialog(new GregorianCalendar());
        }
    }

    @Override
    public void showHistoryBy(final Calendar newDate) {
        monthlyHistory = getDataManager()
                .getMonthlyHistoryFrom(data, (GregorianCalendar) newDate);
        if(monthlyHistory != null) {
            getMvpView().updateUI(monthlyHistory);
        } else {
            getMvpView().showMessage("Load data failed");
        }
    }
}
