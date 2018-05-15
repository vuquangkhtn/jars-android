package com.example.vuquang.jars.activity.data.db.dao;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by VuQuang on 5/14/2018.
 */

public class AppDao {
    private HistoryDao historyDao;
    private UserDao userDao;


    public AppDao(DatabaseReference database, FirebaseAuth auth) {
        userDao = new UserDao(database, auth);
        historyDao = new HistoryDao(database, auth);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public HistoryDao getHistoryDao() {
        return historyDao;
    }
}
