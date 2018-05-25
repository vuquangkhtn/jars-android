package com.example.vuquang.jars.activity.data.db.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.GregorianCalendar;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */
@IgnoreExtraProperties
public class Expense {
    public String expenseId;
    public long amount;
    public String title;
    private long createdAt;
    private long jarType;

    public Expense() {
    }

    public Expense(long amount, String title, long createdAt, int jarType) {
        this.amount = amount;
        this.title = title;
        this.createdAt = createdAt;
        this.jarType = jarType;
    }

    public JarType calculateType() {
        return JarType.values()[(int)jarType];
    }

    public void setCreatedTime(GregorianCalendar calendar) {
        createdAt = calendar.getTimeInMillis();
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public int getJarType() {
        return (int)jarType;
    }
}
