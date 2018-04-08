package com.example.vuquang.jars.activity.userlogin.model;

/**
 * Created by VuQuang on 4/8/2018.
 */

public enum AccessMode {
    OFFLINE("OFFLINE_MODE"),
    ONLINE("ONLINE_MODE");

    AccessMode(String str) {
        this.mode = str;
    }

    String mode;

    public String getMode() {
        return mode;
    }
}