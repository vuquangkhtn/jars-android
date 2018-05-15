package com.example.vuquang.jars.activity.data.db.dao;

import android.util.Log;

import com.example.vuquang.jars.activity.data.db.model.Expense;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.example.vuquang.jars.activity.utils.KeyPref;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VuQuang on 5/14/2018.
 */

public class HistoryDao {
    private final String TAG = this.getClass().getName();

    private DatabaseReference mDatabase;

    public HistoryDao(DatabaseReference databaseReference) {
        this.mDatabase = databaseReference;
    }

    public String insertExpense(Expense expense, String historyId) {
        DatabaseReference expenseEndPoint = mDatabase.child(KeyPref.HISTORY_KEY)
                .child(historyId)
                .child(KeyPref.EXPENSE_KEY);
        String expenseId = expenseEndPoint.push().getKey();
        expense.expenseId = expenseId;
        expenseEndPoint.child(expenseId).setValue(expense);
        return expenseId;
    }

    public String insertHistory(MonthlyHistory history) {
        DatabaseReference historyEndPoint = mDatabase.child(KeyPref.HISTORY_KEY);
        String hisId = historyEndPoint.push().getKey();
        history.historyId = hisId;
        historyEndPoint.child(hisId).setValue(history);
        return hisId;
    }
}
