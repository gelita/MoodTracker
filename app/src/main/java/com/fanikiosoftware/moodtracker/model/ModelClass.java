package com.fanikiosoftware.moodtracker.model;

public class ModelClass {

    private int moodId;
    private String memo;

    public ModelClass(int moodId, String memo){
        this.moodId = moodId;
        this.memo = memo;
    }
//  returns the mood id in int form for access to arrays in the Constants class
    public int getMoodId() {
        return moodId;
    }

//  returns the memo saved in Shared Preferences
    public String getMemo() {
        return  memo;
    }
}