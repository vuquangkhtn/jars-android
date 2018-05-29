package com.example.vuquang.jars.activity.userlogin.splash;

import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.base.BasePresenter;
import com.example.vuquang.jars.activity.data.DataManager;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.GregorianCalendar;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by VuQuang on 5/15/2018.
 */

public class SplashPresenter <V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V> {
    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }

    private void decideNextActivity() {
        if (getDataManager().isLogined()) {
            getMvpView().showLoading();
            getDataManager().getHistoryEndPoint().addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    getMvpView().hideLoading();
                    MonthlyHistory monthlyHistory = getDataManager()
                            .getMonthlyHistoryFrom(dataSnapshot, new GregorianCalendar());
                    if(monthlyHistory != null) {
                        JarsApp.getApp().setHistoryId(monthlyHistory.historyId);
                    } else {
                        getDataManager().createHistory();
                    }

                    getMvpView().goToMain();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            getMvpView().goToLoginActivity();
        }
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        decideNextActivity();
    }
}
