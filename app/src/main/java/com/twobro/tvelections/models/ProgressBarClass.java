package com.twobro.tvelections.models;

import android.support.annotation.ColorRes;

public class ProgressBarClass {
    private String mName = "";
    private String mCountVoting = "0";
    private int mProgress;
    private int mColor = android.R.color.white;

    public ProgressBarClass() {

    }

    public ProgressBarClass(String name, String countVoting) {
        mName = name;
        mCountVoting = countVoting;
    }

    public ProgressBarClass(String name, @ColorRes int color) {
        mName = name;
        mColor = color;
    }

    public ProgressBarClass(String name, String countVoting, @ColorRes int color) {
        mName = name;
        mCountVoting = countVoting;
        mColor = color;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getCountVoting() {
        return mCountVoting;
    }

    public void setCountVoting(String countVoting) {
        mCountVoting = countVoting;
    }

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
        mProgress = progress;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }
}
