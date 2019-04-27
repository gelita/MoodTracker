package com.fanikiosoftware.moodtracker.Utility;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.fanikiosoftware.moodtracker.Controller.MyAlarmReceiver;

import java.util.Calendar;

public class MyCalendarClass {

    private static final String TAG = "MyCalendarClass";

//      set alarm intent so that app updates moods at midnight daily
    public static void setAlarm(Context context) {
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyAlarmReceiver.class);
//      pIntent grants permission to external applications to act on intent
        PendingIntent pIntent = PendingIntent.getBroadcast(context,0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
//      user HOUR_OF_DAY for 24 hr clock & set to 0 for midnight
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        alarmMgr.cancel(pIntent);
        Log.d(TAG,":: prior pIntent cancelled" );
//      RTC fires the pending intent at the specific time but does not wake up the device.
        System.out.println(calendar.getTimeInMillis());
        alarmMgr.setRepeating(
                AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pIntent
        );
        Log.d(TAG,"alarm set");
    }
}