package com.example.vuquang.jars.activity.statistics;

import com.example.vuquang.jars.activity.base.MvpPresenter;

/**
 * Created by VuQuang on 5/15/2018.
 */

public interface StatisticsMvpPresenter<V extends StatisticsMvpView> extends MvpPresenter<V> {
    void onViewPrepared();
}
