package com.example.vuquang.jars.activity.data;

import com.example.vuquang.jars.activity.data.db.AppDbHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by VuQuang on 5/15/2018.
 */

public class AppDataManager extends AppDbHelper implements DataManager {
    private static AppDataManager instance;

    private AppDataManager(DatabaseReference databaseReference, FirebaseAuth auth) {
        super(databaseReference, auth);
    }

    public static AppDataManager getDataManager() {
        if(instance == null) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            instance = new AppDataManager(FirebaseDatabase.getInstance().getReference(),
                    FirebaseAuth.getInstance());
        }
        return instance;
    }

}
