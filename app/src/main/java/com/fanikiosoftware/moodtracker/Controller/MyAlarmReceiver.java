package com.fanikiosoftware.moodtracker.Controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.fanikiosoftware.moodtracker.Utility.Constants;

public class MyAlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "MyAlarmReceiver";
    private SharedPreferences mPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: starting");
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        updateMoods();
    }

//  update moods daily at midnight
    private void updateMoods() {
        for (int i = Constants.titles.length; i > 1; i--) {
//          mood day 6 goes into mood day 7, etc...
            String sharedPrefCopyTo = "PREF_KEY_MOOD" + i;
            String sharedPrefCopyFrom = "PREF_KEY_MOOD" + (i - 1);
            mPreferences.edit().putString(sharedPrefCopyTo, sharedPrefCopyFrom).apply();
        }
    }
}
