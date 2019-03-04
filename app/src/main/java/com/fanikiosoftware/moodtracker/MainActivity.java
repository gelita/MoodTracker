package com.fanikiosoftware.moodtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView ivMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivMood = findViewById(R.id.ivMood);
        ivMood.setImageResource(R.drawable.great_mood);
//        ivGreat = findViewById(R.id.ivGreat);
//        ivGreat.setImageResource(R.drawable.great_mood);
    }
}
