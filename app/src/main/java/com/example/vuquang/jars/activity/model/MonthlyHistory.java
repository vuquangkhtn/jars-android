package com.example.vuquang.jars.activity.model;

import com.example.vuquang.jars.activity.app.JarsApp;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class MonthlyHistory {
    public Calendar currentMonth;
    public long monthlyIncome;
    public List<Jar> jarList;

    public MonthlyHistory() {
        currentMonth = Calendar.getInstance();
        monthlyIncome = JarsApp.getApp().getTotalIncome();
        jarList = new ArrayList<>();
        for (JarType type: JarType.values()) {
            jarList.add(new Jar(type,monthlyIncome));
        }
    }

    public void addExpense(JarType type, Expense expense) {
        for (Jar jar: jarList) {
            if(jar.type == type) {
                jar.expenseList.add(expense);
            }
        }
    }

    public long getTotalExpense() {
        long currentAmount = 0;
        for (Jar jar:jarList) {
            currentAmount += jar.getCurrentAmount();
        }
        return currentAmount;
    }
}
