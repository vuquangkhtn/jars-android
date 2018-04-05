package com.example.vuquang.jars.activity.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CPU10584-local on 04-Apr-18.
 */

public class RealityJar extends Jar {

    public List<Expense> expenseList;

    public RealityJar(int resIcon, String name, long amount, float ratio) {
        super(resIcon, name, amount, ratio);
        expenseList = new ArrayList<>();
    }
}
