package com.example.vuquang.jars.activity.expenses.addexpense;

import android.support.annotation.NonNull;

import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.base.BasePresenter;
import com.example.vuquang.jars.activity.data.DataManager;
import com.example.vuquang.jars.activity.data.db.model.Expense;
import com.example.vuquang.jars.activity.data.db.model.JarType;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.example.vuquang.jars.activity.statistics.adapter.JarAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by VuQuang on 5/25/2018.
 */

public class AddExpensePresenter<V extends AddExpenseMvpView> extends BasePresenter<V> implements AddExpenseMvpPresenter<V> {
    public AddExpensePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onBackPressed() {
        getMvpView().dismissDialog("AddExpense");
    }

    @Override
    public void onAddExpenseClicked(Long amount, String title, String type) {
        getMvpView().showLoading();
        String hisId = JarsApp.getApp().getHistoryId();
        getDataManager()
                .insertExpense(new Expense(amount,title,System.currentTimeMillis(),JarType.getIdFromName(type)), hisId)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        getMvpView().hideLoading();
                        if(task.isSuccessful()) {
                            getMvpView().showMessage("Add expense successful");
                            getMvpView().goToMain();
                        } else {
                            getMvpView().showMessage("Add expense failed");

                        }
                    }
                });

    }

    @Override
    public void onCheckAllEdtFilled() {
        getMvpView().checkAllEdtFilled();
    }
}
