package com.fanikiosoftware.moodtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.fanikiosoftware.moodtracker.R;

public class MainActivity extends AppCompatActivity {

    private ImageView ivMood;
    private ImageButton mAddButton;
    private ImageButton mHistoryButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //checkNewUser();
        //set default color background for first play
        setActivityBackgroundColor(R.color.banana_yellow);
        setContentView(R.layout.activity_main);
        ivMood = findViewById(R.id.ivMood);
        ivMood.setImageResource(R.drawable.great_mood);
        mAddButton = findViewById(R.id.btnAdd);
        mHistoryButton = findViewById(R.id.btnHistory);
        //setup button listeners
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
                Toast.makeText(MainActivity.this, "Hi, I'm the Add button!", Toast.LENGTH_SHORT).show();
            }
        });
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivityIntent = new Intent(MainActivity.this ,HistoryActivity.class);
                startActivity(historyActivityIntent);
            }
        });
    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundResource(color);
    }


}
