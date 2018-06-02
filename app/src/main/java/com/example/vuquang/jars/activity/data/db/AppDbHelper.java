package com.example.vuquang.jars.activity.data.db;

import com.example.vuquang.jars.activity.data.db.dao.AppDao;
import com.example.vuquang.jars.activity.data.db.model.Expense;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.GregorianCalendar;

/**
 * Created by VuQuang on 5/14/2018.
 */

public class AppDbHelper implements DbHelper {
    private AppDao appDao;

    public AppDbHelper(DatabaseReference databaseReference, FirebaseAuth auth) {
        this.appDao = new AppDao(databaseReference, auth);
    }

    @Override
    public String insertUser(String id, String username, String email) {
        return appDao.getUserDao().insertUser(id, username, email);
    }

    @Override
    public String createHistory(long monthlyIncome) {
        return appDao.getHistoryDao().createHistory(monthlyIncome);
    }

    @Override
    public Task<Void> updateMonthlyIncome(String historyId, Long value) {
        return appDao.getHistoryDao().updateMonthlyIncome(historyId, value);
    }

    @Override
    public DatabaseReference getExpenseEndPoint(String historyId) {
        return appDao.getExpenseDao().getExpenseEndPoint(historyId);
    }

    @Override
    public Task<Void> insertExpense(Expense expense, String historyId) {
        return appDao.getExpenseDao().insertExpense(expense, historyId);
    }

    @Override
    public boolean isLogined() {
        return appDao.getUserDao().isLogined();
    }

    @Override
    public Task<AuthResult> signIn(String email, String password) {
        return appDao.getUserDao().signIn(email, password);
    }

    @Override
    public Task<AuthResult> signUp(String email, String password) {
        return appDao.getUserDao().signUp(email, password);
    }

    @Override
    public FirebaseAuth signOut() {
        return appDao.getUserDao().signOut();
    }

    @Override
    public String getUsername() {
        return appDao.getUserDao().getUsername();
    }

    @Override
    public DatabaseReference getHistoryEndPoint() {
        return appDao.getHistoryDao().getHistoryEndPoint();
    }

    @Override
    public MonthlyHistory getMonthlyHistoryFrom(DataSnapshot dataSnapshot, GregorianCalendar month) {
        return appDao.getHistoryDao().getMonthlyHistoryFrom(dataSnapshot, month);
    }

    @Override
    public MonthlyHistory getNearestHistory(DataSnapshot dataSnapshot) {
        return appDao.getHistoryDao().getNearestHistory(dataSnapshot);
    }


}
