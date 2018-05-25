package com.example.vuquang.jars.activity.expenses.addexpense;

import com.example.vuquang.jars.activity.base.DialogMvpView;
import com.example.vuquang.jars.activity.base.MvpView;

/**
 * Created by VuQuang on 5/25/2018.
 */

public interface AddExpenseMvpView extends DialogMvpView {
    void goToMain();
    void checkAllEdtFilled();
}
