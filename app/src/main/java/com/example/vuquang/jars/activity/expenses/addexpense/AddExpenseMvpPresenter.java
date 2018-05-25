package com.example.vuquang.jars.activity.expenses.addexpense;

import com.example.vuquang.jars.activity.base.MvpPresenter;

/**
 * Created by VuQuang on 5/25/2018.
 */

public interface AddExpenseMvpPresenter<V extends AddExpenseMvpView> extends MvpPresenter<V> {
    void onBackPressed();

    void onAddExpenseClicked(Long amount, String title, String type);

    void onCheckAllEdtFilled();
}
