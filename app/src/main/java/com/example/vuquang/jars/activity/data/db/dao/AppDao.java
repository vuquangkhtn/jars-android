package com.example.vuquang.jars.activity.data.db.dao;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by VuQuang on 5/14/2018.
 */

public class AppDao extends UserDao{

    public AppDao(DatabaseReference database, FirebaseAuth auth) {
        super(database, auth);
    }
}
