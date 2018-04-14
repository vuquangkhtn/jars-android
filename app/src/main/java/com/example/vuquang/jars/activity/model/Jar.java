package com.example.vuquang.jars.activity.model;

import com.example.vuquang.jars.activity.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class Jar {
    public long totalAmount;
    private JarType type;
    public List<Expense> expenseList;

    public Jar(JarType type, long total) {
        if(type.getId() == -1) {
            return;
        }
        this.type = type;
        this.totalAmount = Math.round(total*type.getRatio());
        expenseList = new ArrayList<>();
    }

    public long getCurrentAmount() {
        long currentAmount = 0;
        for (Expense expense:expenseList) {
            currentAmount += expense.amount;
        }
        return currentAmount;
    }

    public String getLongName() {
        return type.getName()+" ("+Math.round(type.getRatio()*100)+"%)";
    }

    public String getName() {
        return type.getName();
    }

    public int getResIcon() {
        return type.getResIdIcon();
    }

    public float getRatio() {
        return type.getRatio();
    }

}
