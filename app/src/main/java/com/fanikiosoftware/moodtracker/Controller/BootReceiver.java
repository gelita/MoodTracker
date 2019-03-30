package com.fanikiosoftware.moodtracker.Controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("BootReceiver:: BOOT_COMPLETED Broadcast rec'd");
//        MainActivity mainActivity = new MainActivity();
//        mainActivity.setAlarm();
        setAlarm(context);
    }

    public void setAlarm(Context context) {
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyAlarmReceiver.class);
//      pIntent grants permission to external applications to act on intent
        PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
//      user HOUR_OF_DAY for 24 hr clock & set to 0 for midnight
        calendar.add(Calendar.HOUR_OF_DAY, 0);
//      RTC fires the pending intent at the specific time but does not wake up the device.
        alarmMgr.setRepeating(
                AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pIntent
        );
        System.out.println("BootReceiver:: alarm reset");
    }
}
