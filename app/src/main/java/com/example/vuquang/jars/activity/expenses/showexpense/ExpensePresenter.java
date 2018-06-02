package com.example.vuquang.jars.activity.expenses.showexpense;

import com.example.vuquang.jars.activity.base.BasePresenter;
import com.example.vuquang.jars.activity.data.DataManager;
import com.example.vuquang.jars.activity.data.db.model.Expense;
import com.example.vuquang.jars.activity.data.db.model.JarType;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by VuQuang on 5/27/2018.
 */

public class ExpensePresenter<V extends ExpenseMvpView> extends BasePresenter<V> implements ExpenseMvpPresenter<V> {

    private MonthlyHistory history;

    public ExpensePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadExpenseList() {
        getDataManager().getHistoryEndPoint().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MonthlyHistory monthlyHistory = getDataManager()
                        .getMonthlyHistoryFrom(dataSnapshot, new GregorianCalendar());
                if(monthlyHistory != null) {
                    history = monthlyHistory;
                    getMvpView().setDefaultTab();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onTabSelected(int position) {
        if(history != null) {
            List<Expense> expenses = history.findExpensesListBy(JarType.values()[position]);
            if(expenses.isEmpty()) {
                getMvpView().switchToEmptyMode();
            } else {
                getMvpView().switchToDataMode();
                getMvpView().setExpenseListBy(expenses);
            }

        }
    }

    @Override
    public void onDismissAddExpenseDialog() {
        onTabSelected(JarType.values().length - 1);
    }
}
