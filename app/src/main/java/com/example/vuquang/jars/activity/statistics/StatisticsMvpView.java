package com.example.vuquang.jars.activity.statistics;

import com.example.vuquang.jars.activity.base.MvpView;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;

import java.util.GregorianCalendar;

/**
 * Created by VuQuang on 5/15/2018.
 */

public interface StatisticsMvpView extends MvpView {
    void updateUI(MonthlyHistory history);

    void showDatePickerDialog(GregorianCalendar calendar);
}
