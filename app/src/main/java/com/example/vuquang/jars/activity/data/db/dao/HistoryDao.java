package com.example.vuquang.jars.activity.data.db.dao;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.vuquang.jars.activity.base.BaseFragment;
import com.example.vuquang.jars.activity.data.db.model.Expense;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.example.vuquang.jars.activity.utils.KeyPref;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by VuQuang on 5/14/2018.
 */

public class HistoryDao {
    private final String TAG = this.getClass().getName();
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    public HistoryDao(DatabaseReference databaseReference, FirebaseAuth auth) {
        this.mDatabase = databaseReference;
        this.mAuth = auth;
    }

    private MonthlyHistory init() {
        return new MonthlyHistory(mAuth.getUid());
    }

    private String insertHistory(MonthlyHistory history) {
        DatabaseReference historyEndPoint = mDatabase.child(KeyPref.HISTORY_KEY);
        String hisId = historyEndPoint.push().getKey();
        history.historyId = hisId;
        historyEndPoint.child(hisId).setValue(history);
        return hisId;
    }

    public Task<Void> updateMonthlyIncome(String historyId, Long incomeVal) {
        DatabaseReference historyEndPoint = mDatabase.child(KeyPref.HISTORY_KEY).child(historyId);
        return historyEndPoint.child("monthlyIncome").setValue(incomeVal);
    }

    public String createHistory() {
        return insertHistory(init());
    }

    public DatabaseReference getHistoryEndPoint() {
        return mDatabase.child(KeyPref.HISTORY_KEY);
    }

    public MonthlyHistory getMonthlyHistoryFrom(DataSnapshot dataSnapshot, GregorianCalendar calendar) {
        String uid = mAuth.getUid();
        MonthlyHistory resultHistory = null;
        for (DataSnapshot note: dataSnapshot.getChildren()){
            MonthlyHistory history = note.getValue(MonthlyHistory.class);
            for (DataSnapshot expenseNote: note.child(KeyPref.EXPENSE_KEY).getChildren()) {
                Expense expense = expenseNote.getValue(Expense.class);
                history.expenseList.add(expense);
            }
            if(history != null && history.userId.equals(uid)) {
                if(resultHistory == null) {
                    resultHistory = history;
                } else {
                    if(history.currentMonth > resultHistory.currentMonth) {
                        resultHistory = history;
                    }
                }
            }
        }
        if(resultHistory != null) {
            GregorianCalendar monthFromHis = resultHistory.monthToCalendar();
            if (calendar.get(Calendar.YEAR) == monthFromHis.get(Calendar.YEAR)
                   || calendar.get(Calendar.MONTH) == monthFromHis.get(Calendar.MONTH)) {
                return resultHistory;
            } else {
                return null;
            }
        }
        return null;
    }


}
