package com.example.vuquang.jars.activity.data.db;

import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.example.vuquang.jars.activity.data.db.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by VuQuang on 5/14/2018.
 */

public interface DbHelper {
    String insertUser(String id, String email, String password);

    String insertExpense(int userId);

    boolean isLogined();

    Task<AuthResult> signIn(String email, String password);

    String getUsername();

    DatabaseReference getHistoryEndPoint();

    MonthlyHistory getMonthlyHistoryFrom(DataSnapshot dataSnapshot);
}
