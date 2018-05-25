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
    public long currentMonth;
    public long monthlyIncome;
    public List<Expense> expenseList;

    public MonthlyHistory() {
        this.userId = null;
        GregorianCalendar calendar = new GregorianCalendar();
        currentMonth = calendar.getTimeInMillis();
        monthlyIncome = 0;
        expenseList = new ArrayList<>();
    }

    public MonthlyHistory(String userId) {
        this.userId = userId;
        GregorianCalendar calendar = new GregorianCalendar();
        currentMonth = calendar.getTimeInMillis();
        monthlyIncome = 0;
        expenseList = new ArrayList<>();
    }

    public long calculateTotalExpense() {
        long currentAmount = 0;
        for (Expense expense:expenseList) {
            currentAmount += expense.amount;
        }
        return currentAmount;
    }

    public GregorianCalendar monthToCalendar() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(currentMonth);
        return calendar;
    }

    public List<Expense> findExpensesListBy(JarType type) {
        if(type == JarType.ALL) {
            return expenseList;
        } else {
            List<Expense> list = new ArrayList<>();
            for (Expense expense :
                    expenseList) {
                if(expense.calculateType() == type) {
                    list.add(expense);
                }
            }
            return list;
        }
    }

    public long calculateCurrentAmountBy(JarType type) {
        if(type == JarType.ALL) {
            return calculateTotalExpense();
        } else {
            long total = 0;
            for (Expense expense :
                    findExpensesListBy(type)) {
                total += expense.amount;
            }
            return total;
        }
    }

    public long calculateTotalIncomeBy(JarType type) {
        if(type == JarType.ALL) {
            return monthlyIncome;
        } else {
            return (long) (monthlyIncome*type.getRatio());
        }
    }
}
