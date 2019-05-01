package com.fanikiosoftware.moodtracker.Controller;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.fanikiosoftware.moodtracker.R;
import com.fanikiosoftware.moodtracker.Utility.Constants;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class PieChartActivity extends AppCompatActivity {

    PieChartView pieChartView;
    private SharedPreferences mPreferences;
    int[] moodId = new int[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        pieChartView = findViewById(R.id.chart);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        getData();
        //list to store mood data for the pie chart
        List chartData = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            int color = moodId[i];
            if (color == 0) {
                color = Color.YELLOW;
            } else if (color == 1) {
                color = Color.GREEN;
            } else if (color == 2) {
                color = Color.BLUE;
            } else if (color == 3) {
                color = Color.DKGRAY;
            } else if (color == 4) {
                color = Color.RED;
            } else if (color == 5) {
                color = Color.LTGRAY;
            }
            System.out.println("color is " + color);
            chartData.add(new SliceValue(10, color));
            PieChartData pieChartData = new PieChartData(chartData);
            pieChartData.setHasCenterCircle(true).setCenterText1("My Moods This Week")
                    .setCenterText1FontSize(20).setCenterText1Color(Color.BLUE);
            pieChartView.setPieChartData(pieChartData);
        }
    }

    //  get mood data from sharedPrefs
    public void getData() {
        moodId[0] = mPreferences.getInt(Constants.PREF_KEY_MOOD7, 5);
        moodId[1] = mPreferences.getInt(Constants.PREF_KEY_MOOD6, 5);
        moodId[2] = mPreferences.getInt(Constants.PREF_KEY_MOOD5, 5);
        moodId[3] = mPreferences.getInt(Constants.PREF_KEY_MOOD4, 5);
        moodId[4] = mPreferences.getInt(Constants.PREF_KEY_MOOD3, 5);
        moodId[5] = mPreferences.getInt(Constants.PREF_KEY_MOOD2, 5);
        moodId[6] = mPreferences.getInt(Constants.PREF_KEY_MOOD1, 5);
    }
}