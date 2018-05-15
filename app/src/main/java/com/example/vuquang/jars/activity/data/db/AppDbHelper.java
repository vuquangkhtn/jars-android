package com.example.vuquang.jars.activity.data.db;

import com.example.vuquang.jars.activity.data.db.dao.AppDao;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.example.vuquang.jars.activity.data.db.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
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
        return appDao.insertUser(id, username, email);
    }

    @Override
    public List<MonthlyHistory> getHistoriesBy(int userId) {
        return null;
    }

    @Override
    public MonthlyHistory getCurrentHistory(int userId) {
        return null;
    }

    @Override
    public String insertExpense(int userId) {
        return null;
    }

    @Override
    public boolean isLogined() {
        return appDao.isLogined();
    }

    @Override
    public Task<AuthResult> signIn(String email, String password) {
        return appDao.signIn(email, password);
    }
}
