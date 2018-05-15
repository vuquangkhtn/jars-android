package com.example.vuquang.jars.activity.data.db.dao;

import com.example.vuquang.jars.activity.data.db.model.Expense;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.example.vuquang.jars.activity.utils.KeyPref;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

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

    public DatabaseReference getHistoryEndPoint() {
        return mDatabase.child(KeyPref.HISTORY_KEY);
    }

    public MonthlyHistory getMonthlyHistoryFrom(DataSnapshot dataSnapshot) {
        String uid = mAuth.getUid();
        MonthlyHistory curHistory = null;
        for (DataSnapshot note: dataSnapshot.getChildren()){
            MonthlyHistory history = note.getValue(MonthlyHistory.class);
            if(history != null && history.userId.equals(uid)) {
                if(curHistory == null) {
                    curHistory = history;
                } else {
                    if(history.currentMonth > curHistory.currentMonth) {
                        curHistory = history;
                    }
                }
            }
        }

        if(curHistory == null) {
            curHistory = new MonthlyHistory(uid);
            insertHistory(curHistory);
        }
        return curHistory;
    }
}
