package com.trzewik.quizwirtualnapolska.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Rate {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo
    private int valueFrom;
    @ColumnInfo
    private int valueTo;
    @ColumnInfo
    private String message;

    public Rate() {
    }

    @Ignore
    public Rate(int valueFrom, int valueTo, String message) {
        this.valueFrom = valueFrom;
        this.valueTo = valueTo;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getValueFrom() {
        return valueFrom;
    }

    public void setValueFrom(int valueFrom) {
        this.valueFrom = valueFrom;
    }

    public int getValueTo() {
        return valueTo;
    }

    public void setValueTo(int valueTo) {
        this.valueTo = valueTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
