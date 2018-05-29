package com.example.vuquang.jars.activity.expenses;

import com.example.vuquang.jars.activity.base.MvpView;
import com.example.vuquang.jars.activity.data.db.model.Expense;

import java.util.List;

/**
 * Created by VuQuang on 5/27/2018.
 */

public interface ExpenseMvpView extends MvpView {
    void setExpenseListBy(List<Expense> list);

    void setDefaultTab();
}
