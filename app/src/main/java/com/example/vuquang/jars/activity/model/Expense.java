package com.example.vuquang.jars.activity.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class Expense implements Parcelable {
    public long amount;
    public String title;
    public JarType jarType;

    protected Expense(Parcel in) {
        amount = in.readLong();
        title = in.readString();
        this.jarType = (JarType) in.readSerializable();
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
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(amount);
        parcel.writeString(title);
        parcel.writeSerializable(this.jarType);
    }
}
