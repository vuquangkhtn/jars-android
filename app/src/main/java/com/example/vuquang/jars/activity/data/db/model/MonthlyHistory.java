package com.example.vuquang.jars.activity.data.db.model;

import com.example.vuquang.jars.activity.app.JarsApp;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
        List<Expense> list;
        if(type == JarType.ALL) {
            list = new ArrayList<>(expenseList.values());
        } else {
            list = new ArrayList<>();
            for (Expense expense :
                    expenseList.values()) {
                if(expense.calculateType() == type) {
                    list.add(expense);
                }
            }

        }
        Collections.sort(list, new Comparator<Expense>() {
            @Override
            public int compare(Expense expense1, Expense expense2) {
                Calendar cal1 = Calendar.getInstance();
                cal1.setTimeInMillis(expense1.getCreatedAt());

                Calendar cal2 = Calendar.getInstance();
                cal2.setTimeInMillis(expense2.getCreatedAt());

                int isSameDate = cal2.get(Calendar.DATE) - cal1.get(Calendar.DATE);
                if(isSameDate == 0) {
                    int isSameHour = cal2.get(Calendar.HOUR) - cal1.get(Calendar.HOUR);
                    if(isSameHour == 0) {
                        int isSameMin = cal2.get(Calendar.MINUTE) - cal1.get(Calendar.MINUTE);
                        if(isSameMin == 0) {
                            return cal2.get(Calendar.SECOND) - cal1.get(Calendar.SECOND);
                        } else {
                            return isSameMin;
                        }
                    } else {
                        return isSameHour;
                    }
                } else {
                    return isSameDate;
                }
            }
        });
        return list;
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
