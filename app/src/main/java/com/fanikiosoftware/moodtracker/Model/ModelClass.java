package com.fanikiosoftware.moodtracker.Model;

public class ModelClass {

    private int moodId;
    private String memo;

    public ModelClass(int moodId, String memo){
        this.moodId = moodId;
        this.memo = memo;
    }

    public int getMoodId() {
        return moodId;
    }

    public String getMemo() {
        return  memo;
    }
}
