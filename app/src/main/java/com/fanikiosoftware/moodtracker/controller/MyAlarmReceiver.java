package com.fanikiosoftware.moodtracker.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fanikiosoftware.moodtracker.utility.Constants;

public class MyAlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "MyAlarmReceiver";
    private SharedPreferences mPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        updateMoods();
    }

//    update moods daily at midnight
    private void updateMoods() {
        int mood7 = mPreferences.getInt(Constants.PREF_KEY_MOOD6, 5);
        int mood6 = mPreferences.getInt(Constants.PREF_KEY_MOOD5, 5);
        int mood5 = mPreferences.getInt(Constants.PREF_KEY_MOOD4, 5);
        int mood4 = mPreferences.getInt(Constants.PREF_KEY_MOOD3, 5);
        int mood3 = mPreferences.getInt(Constants.PREF_KEY_MOOD2, 5);
        int mood2 = mPreferences.getInt(Constants.PREF_KEY_MOOD1, 5);
        int mood1 = mPreferences.getInt(Constants.PREF_KEY_CURRENT_MOOD, 5);

        String memo7 = mPreferences.getString(Constants.PREF_KEY_MEMO6, null);
        String memo6 = mPreferences.getString(Constants.PREF_KEY_MEMO5, null);
        String memo5 = mPreferences.getString(Constants.PREF_KEY_MEMO4, null);
        String memo4 = mPreferences.getString(Constants.PREF_KEY_MEMO3, null);
        String memo3 = mPreferences.getString(Constants.PREF_KEY_MEMO2, null);
        String memo2 = mPreferences.getString(Constants.PREF_KEY_MEMO1, null);
        String memo1 = mPreferences.getString(Constants.PREF_KEY_CURRENT_MEMO, null);

        mPreferences.edit().putInt(Constants.PREF_KEY_MOOD7, mood7).apply();
        mPreferences.edit().putInt(Constants.PREF_KEY_MOOD6, mood6).apply();
        mPreferences.edit().putInt(Constants.PREF_KEY_MOOD5, mood5).apply();
        mPreferences.edit().putInt(Constants.PREF_KEY_MOOD4, mood4).apply();
        mPreferences.edit().putInt(Constants.PREF_KEY_MOOD3, mood3).apply();
        mPreferences.edit().putInt(Constants.PREF_KEY_MOOD2, mood2).apply();
        mPreferences.edit().putInt(Constants.PREF_KEY_MOOD1, mood1).apply();
//      reset the current mood to 5 so that if mood is not saved by user before next alarm
//      the mood will show as grey(the default empty mood) in the history view
        mPreferences.edit().putInt(Constants.PREF_KEY_CURRENT_MOOD, 5).apply();

        mPreferences.edit().putString(Constants.PREF_KEY_MEMO7, memo7).apply();
        mPreferences.edit().putString(Constants.PREF_KEY_MEMO6, memo6).apply();
        mPreferences.edit().putString(Constants.PREF_KEY_MEMO5, memo5).apply();
        mPreferences.edit().putString(Constants.PREF_KEY_MEMO4, memo4).apply();
        mPreferences.edit().putString(Constants.PREF_KEY_MEMO3, memo3).apply();
        mPreferences.edit().putString(Constants.PREF_KEY_MEMO2, memo2).apply();
        mPreferences.edit().putString(Constants.PREF_KEY_MEMO1, memo1).apply();
//      reset the current memo to null
        mPreferences.edit().putString(Constants.PREF_KEY_CURRENT_MEMO, null).apply();
//      reset the current selectedMoodId to default Great Mood after saving user prefs
        MainActivity.selectedMoodId = Constants.MOOD_GREAT;
    }
}