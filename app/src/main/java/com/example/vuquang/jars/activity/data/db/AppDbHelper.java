package com.example.vuquang.jars.activity.data.db;

import com.example.vuquang.jars.activity.data.db.dao.AppDao;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.example.vuquang.jars.activity.data.db.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

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
    public String insertExpense(int userId) {
        return null;
    }

    @Override
    public String createHistory() {
        return appDao.getHistoryDao().createHistory();
    }

    @Override
    public Task<Void>  updateHistory(MonthlyHistory monthlyHistory) {
        return appDao.getHistoryDao().updateHistory(monthlyHistory);
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
    public MonthlyHistory getMonthlyHistoryFrom(DataSnapshot dataSnapshot) {
        return appDao.getHistoryDao().getMonthlyHistoryFrom(dataSnapshot);
    }
}
