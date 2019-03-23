package com.fanikiosoftware.moodtracker.Model;

public class ModelClass {

    private int imageButton;
    private String title;

    public ModelClass(int imageButton, String title){
        this.imageButton = imageButton;
        this.title = title;
    }

    public int getImageButton() {
        return imageButton;
    }

    public String getTitle() {
        return  title;
    }
}
