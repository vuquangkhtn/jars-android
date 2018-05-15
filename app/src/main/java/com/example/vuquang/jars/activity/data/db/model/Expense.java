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
    private int jarType;

    public JarType getType() {
        return JarType.values()[jarType];
    }

    public void setJarType(JarType type) {
        jarType = type.getId();
    }

    public void setCreatedTime(GregorianCalendar calendar) {
        createdAt = calendar.getTimeInMillis();
    }

    public GregorianCalendar getCreatedTimeInMillis() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(createdAt);
        return calendar;
    }
}
