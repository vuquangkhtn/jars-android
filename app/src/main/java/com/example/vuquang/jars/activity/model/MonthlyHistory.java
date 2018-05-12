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
    public List<Expense> expenseList;

    public MonthlyHistory() {
        currentMonth = Calendar.getInstance();
        monthlyIncome = JarsApp.getApp().getTotalIncome();
        expenseList = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenseList.add(expense);
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
                if(expense.jarType == type) {
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
