package com.example.vuquang.jars.activity.data.db.model;

import com.example.vuquang.jars.activity.app.JarsApp;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
    public HashMap<String,Expense> expenseList;

    public MonthlyHistory() {
        GregorianCalendar calendar = new GregorianCalendar();
        currentMonth = calendar.getTimeInMillis();
        monthlyIncome = 0;
        expenseList = new HashMap<>();
    }

    public MonthlyHistory(String userId) {
        this.userId = userId;
        GregorianCalendar calendar = new GregorianCalendar();
        currentMonth = calendar.getTimeInMillis();
        monthlyIncome = 0;
        expenseList = new HashMap<>();
    }

    public long calculateTotalExpense() {
        long currentAmount = 0;
        for (Expense expense:expenseList.values()) {
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
            return new ArrayList<>(expenseList.values());
        } else {
            List<Expense> list = new ArrayList<>();
            for (Expense expense :
                    expenseList.values()) {
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
