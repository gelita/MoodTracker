package com.fanikiosoftware.moodtracker.Controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.fanikiosoftware.moodtracker.Utility.MyCalendarClass;

import java.util.Calendar;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("BootReceiver:: BOOT_COMPLETED Broadcast rec'd, setting alarm");
        MyCalendarClass.setAlarm(context);
    }


}
