package com.fanikiosoftware.moodtracker.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fanikiosoftware.moodtracker.R;

public class HistoryActivity extends AppCompatActivity {

    //message buttons by day btn1=1day ago..btn7=week ago
    private Button btnMemo1,btnMemo2,btnMemo3,btnMemo4,btnMemo5,btnMemo6,btnMemo7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setActivityBackgroundColor(R.color.light_grey);
    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundResource(color);
    }
}
