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
        Intent intent = new Intent(context, MyAlarmReceiver.class);
//      pIntent grants permission to external applications to act on intent
//      create the pending intent w/ the intent that was just created in this class
        PendingIntent pIntent = PendingIntent.getBroadcast(
                context,
                100,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//      user HOUR_OF_DAY for 24 hr clock & set to 0 for midnight
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
//      add 1 day to the calendar instance in order to prevent alarm from being called for past
//        scheduled intent
        calendar.add(Calendar.DATE,1 );
//      RTC fires the pending intent at the specific time but does not wake up the device.
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmMgr.cancel(pIntent);
        alarmMgr.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pIntent
        );
        Log.d(TAG,"alarm set");
    }
}