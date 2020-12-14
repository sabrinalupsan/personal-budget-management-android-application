package com.example.seminar_4;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "users")
public class User {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "Id")
    private String mId;

    @ColumnInfo(name = "Limit")
    private String mLimit;

    @ColumnInfo(name = "Amount")
    private String mAmount;

    @ColumnInfo(name = "Type")
    private String mType;

    @ColumnInfo(name = "Category")
    private String mCategory;

    @ColumnInfo(name = "Date")
    private String mDate;

    @Ignore
    public User(String userName) {
        mId = UUID.randomUUID().toString();
    }

    public User(String id, String limit, String amount, String type, String category, String date) {
        this.mId = id;
        this.mLimit = limit;
        this.mAmount = amount;
        this.mType = type;
        this.mCategory = category;
        this.mDate = date;
    }

    @NonNull
    public String getId() {
        return mId;
    }


    public String getMLimit() {
        return mLimit;
    }

    public void setMLimit(String mLimit) {
        this.mLimit = mLimit;
    }

    public String getMAmount() {
        return mAmount;
    }

    public void setMAmount(String mAmount) {
        this.mAmount = mAmount;
    }

    public String getMType() {
        return mType;
    }

    public void setMType(String mType) {
        this.mType = mType;
    }

    public String getMCategory() {
        return mCategory;
    }

    public void setMCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getMDate() {
        return mDate;
    }

    public void setMDate(String mDate) {
        this.mDate = mDate;
    }

}

