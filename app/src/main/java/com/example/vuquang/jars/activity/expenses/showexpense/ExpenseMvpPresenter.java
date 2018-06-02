package com.example.vuquang.jars.activity.expenses.showexpense;

import com.example.vuquang.jars.activity.base.MvpPresenter;

/**
 * Created by VuQuang on 5/27/2018.
 */

public interface ExpenseMvpPresenter<V extends ExpenseMvpView> extends MvpPresenter<V>{
    void loadExpenseList();

    void onTabSelected(int pos);

    void onDismissAddExpenseDialog();
}
