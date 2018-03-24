package com.example.vuquang.jars.activity.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import com.example.vuquang.jars.activity.app.JarsApp;

/**
 * Created by CPU10584-local on 11-Jan-18.
 */

public class SharePrefHelper {
    private static SharePrefHelper instance;
    private SharedPreferences mSharedPreferences;

    private SharePrefHelper() {
        mSharedPreferences = JarsApp.getApp().getSharedPreferences("CommonPref", 0);
    }

    public static SharePrefHelper get() {
        if(instance == null) {
            instance = new SharePrefHelper();
        }
        return instance;
    }

    public final boolean getBoolean(String key, boolean val) {
        return this.mSharedPreferences.getBoolean(key, val);
    }

    public final boolean setBoolean(String key, boolean val) {
        return this.mSharedPreferences.edit().putBoolean(key, val).commit();
    }

    public final boolean setLong(String key, long val) {
        return this.mSharedPreferences.edit().putLong(key, val).commit();
    }

    public final long getLong(String key, long val) {
        return this.mSharedPreferences.getLong(key, val);
    }

    public final boolean setInt(String key, int val) {
        return this.mSharedPreferences.edit().putInt(key, val).commit();
    }

    public final boolean setString(String key, String val) {
        return this.mSharedPreferences.edit().putString(key, val).commit();
    }

    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public String getString(String key, String def) {
        return mSharedPreferences.getString(key, def);
    }

    public int getInt(String key, int defVal) {
        return mSharedPreferences.getInt(key, defVal);
    }


    public byte[] getByteArray(String key) {
        String data = getString(key);
        if(!TextUtils.isEmpty(data)) {
            byte[] array = Base64.decode(data, Base64.NO_WRAP);
            return array;
        }
        return null;
    }

    public void setByteArray(String key, byte[] arr) {
        if(arr != null && arr.length > 0) {
            String saveThis = Base64.encodeToString(arr, Base64.NO_WRAP);
            setString(key, saveThis);
        }
    }
}
