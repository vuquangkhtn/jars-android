package com.example.vuquang.jars.activity.userlogin.splash;

import com.example.vuquang.jars.activity.base.BasePresenter;
import com.example.vuquang.jars.activity.data.DataManager;

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
            getMvpView().goToMain();
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
