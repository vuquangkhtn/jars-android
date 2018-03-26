package com.example.vuquang.jars.activity.theoryjar.model;

/**
 * Created by CPU10584-local on 26-Mar-18.
 */

public class Jar {
    public int resIcon;
    public String name;
    public long amount;
    public float ratio;

    public Jar(int resIcon, String name, long amount, float ratio) {
        this.resIcon = resIcon;
        this.name = name;
        this.amount = amount;
        this.ratio = ratio;
    }
}
