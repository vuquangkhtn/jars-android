package com.example.vuquang.jars.activity.data.db.dao;

import com.example.vuquang.jars.activity.data.db.model.Expense;
import com.example.vuquang.jars.activity.utils.KeyPref;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by VuQuang on 5/17/2018.
 */

public class ExpenseDao {

    private final String TAG = this.getClass().getName();
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    public ExpenseDao(DatabaseReference databaseReference, FirebaseAuth auth) {
        this.mDatabase = databaseReference;
        this.mAuth = auth;
    }

    public DatabaseReference getExpenseEndPoint(String historyId) {

        return mDatabase.child(KeyPref.HISTORY_KEY)
                .child(historyId)
                .child(KeyPref.EXPENSE_KEY);
    }

    public String insertExpense(Expense expense, String historyId) {
        DatabaseReference expenseEndPoint = getExpenseEndPoint(historyId);
        String expenseId = expenseEndPoint.push().getKey();
        expense.expenseId = expenseId;
        expenseEndPoint.child(expenseId).setValue(expense);
        return expenseId;
    }
}
