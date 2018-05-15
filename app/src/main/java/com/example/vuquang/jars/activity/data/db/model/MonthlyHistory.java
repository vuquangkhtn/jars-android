package com.example.vuquang.jars.activity.data.db.model;

import com.example.vuquang.jars.activity.app.JarsApp;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */
@IgnoreExtraProperties
public class MonthlyHistory {
    public String historyId;
    public String userId;
    private long currentMonth;
    public long monthlyIncome;
    public List<Expense> expenseList;

    public MonthlyHistory(String userId) {
        this.userId = userId;
        currentMonth = new GregorianCalendar().getTimeInMillis();
        monthlyIncome = JarsApp.getApp().getTotalIncome();
        expenseList = new ArrayList<>();
    }

    public void setCurrentMonth(GregorianCalendar calendar) {
        currentMonth = calendar.getTimeInMillis();
    }

    public GregorianCalendar getCurrentMonth() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(currentMonth);
        return calendar;
    }

    public long getTotalExpense() {
        long currentAmount = 0;
        for (Expense expense:expenseList) {
            currentAmount += expense.amount;
        }
        return currentAmount;
    }

    public List<Expense> getExpensesListBy(JarType type) {
        if(type == JarType.ALL) {
            return expenseList;
        } else {
            List<Expense> list = new ArrayList<>();
            for (Expense expense :
                    expenseList) {
                if(expense.getType() == type) {
                    list.add(expense);
                }
            }
            return list;
        }
    }

    public long getCurrentAmountBy(JarType type) {
        if(type == JarType.ALL) {
            return getTotalExpense();
        } else {
            long total = 0;
            for (Expense expense :
                    getExpensesListBy(type)) {
                total += expense.amount;
            }
            return total;
        }
    }

    public long getTotalIncomeBy(JarType type) {
        if(type == JarType.ALL) {
            return monthlyIncome;
        } else {
            return (long) (monthlyIncome*type.getRatio());
        }
    }
}
