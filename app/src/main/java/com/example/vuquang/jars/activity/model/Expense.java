package com.example.vuquang.jars.activity.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CPU10584-local on 04-Apr-18.
 */

public class Expense implements Parcelable {
    private long amount;
    private String title;
    private Jar.JarType type;

    protected Expense(Parcel in) {
        this.amount = in.readLong();
        this.title = in.readString();
        this.type = (Jar.JarType) in.readSerializable();
    }

    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.amount);
        dest.writeString(this.title);
        dest.writeSerializable(this.type);
    }
}
