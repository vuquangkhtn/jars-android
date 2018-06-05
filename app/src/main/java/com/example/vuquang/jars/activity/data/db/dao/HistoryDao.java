package com.example.vuquang.jars.activity.data.db.dao;

import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;
import com.example.vuquang.jars.activity.utils.KeyPref;
import com.example.vuquang.jars.activity.utils.NetworkUtils;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by VuQuang on 5/14/2018.
 */

public class HistoryDao {
    private final String TAG = this.getClass().getName();
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    public HistoryDao(DatabaseReference databaseReference, FirebaseAuth auth) {
        this.mDatabase = databaseReference;
        this.mAuth = auth;
    }

    private MonthlyHistory init() {
        return new MonthlyHistory(mAuth.getUid());
    }

    private String insertHistory(MonthlyHistory history) {
        DatabaseReference historyEndPoint = getHistoriesEndPoint();
        String hisId = historyEndPoint.push().getKey();
        history.historyId = hisId;
        historyEndPoint.child(hisId).setValue(history);
        return hisId;
    }

    public Task<Void> updateMonthlyIncome(String historyId, Long incomeVal) {
        DatabaseReference historyEndPoint = getHistoriesEndPoint().child(historyId);
        DatabaseReference incomeEndPoint = historyEndPoint.child("monthlyIncome");

        return incomeEndPoint.setValue(incomeVal);
    }

    public String createHistory(long monthlyIncome) {
        MonthlyHistory monthlyHistory = init();
        monthlyHistory.monthlyIncome = monthlyIncome;
        return insertHistory(monthlyHistory);
    }

    public DatabaseReference getHistoriesEndPoint() {
        return mDatabase.child(KeyPref.HISTORY_KEY);
    }

    public MonthlyHistory getMonthlyHistoryFrom(DataSnapshot dataSnapshot, GregorianCalendar calendar) {
        String uid = mAuth.getUid();
        for (DataSnapshot note: dataSnapshot.getChildren()){
            MonthlyHistory history = note.getValue(MonthlyHistory.class);
            if(history != null && history.userId.equals(uid)) {
                GregorianCalendar monthFromHis = history.monthToCalendar();
                if (calendar.get(Calendar.YEAR) == monthFromHis.get(Calendar.YEAR)
                        && calendar.get(Calendar.MONTH) == monthFromHis.get(Calendar.MONTH)) {
                    return history;
                }
            }
        }
        return null;
    }

    public MonthlyHistory getNearestHistory(DataSnapshot dataSnapshot) {
        String uid = mAuth.getUid();
        MonthlyHistory result = null;
        for (DataSnapshot note: dataSnapshot.getChildren()){
            MonthlyHistory history = note.getValue(MonthlyHistory.class);
            if(history != null && history.userId.equals(uid)) {
                if(result == null) {
                    result = history;
                } else {
                    if(result.currentMonth < history.currentMonth) {
                        result = history;
                    }
                }
            }
        }
        return result;
    }


}
