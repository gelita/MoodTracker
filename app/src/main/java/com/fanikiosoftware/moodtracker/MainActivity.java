package com.fanikiosoftware.moodtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView ivMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityBackgroundColor(R.color.banana_yellow);
        setContentView(R.layout.activity_main);
        ivMood = findViewById(R.id.ivMood);
        ivMood.setImageResource(R.drawable.great_mood);
    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundResource(color);
    }
}
