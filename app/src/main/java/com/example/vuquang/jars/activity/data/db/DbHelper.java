package com.example.vuquang.jars.activity.data.db;

import com.example.vuquang.jars.activity.data.db.model.Expense;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.example.vuquang.jars.activity.data.db.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.GregorianCalendar;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by VuQuang on 5/14/2018.
 */

public interface DbHelper {

    String getUsername();

    String insertUser(String id, String email, String password);

    boolean isLogined();

    Task<AuthResult> signIn(String email, String password);

    Task<AuthResult> signUp(String email, String password);

    FirebaseAuth signOut();

    DatabaseReference getHistoryEndPoint();

    public MonthlyHistory getMonthlyHistoryFrom(DataSnapshot dataSnapshot, GregorianCalendar month);

    public MonthlyHistory getNearestHistory(DataSnapshot dataSnapshot);

    public String createHistory(long monthlyIncome);

    Task<Void> updateMonthlyIncome(String historyId, Long value);

    public DatabaseReference getExpenseEndPoint(String historyId);

    public Task<Void> insertExpense(Expense expense, String historyId);
}
