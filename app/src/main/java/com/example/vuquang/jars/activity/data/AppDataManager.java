package com.example.vuquang.jars.activity.data;

import com.example.vuquang.jars.activity.data.db.AppDbHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by VuQuang on 5/15/2018.
 */

public class AppDataManager extends AppDbHelper implements DataManager {
    public AppDataManager(DatabaseReference databaseReference, FirebaseAuth auth) {
        super(databaseReference, auth);
    }
}
