package com.fanikiosoftware.moodtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Angela Rosas on 3/12/2019.
 *
 *  MyAlarmReceiver class that will be executed by the alarm
 *  and will launch our IntentService
 *
 */

public class MyAlarmReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE = 222;
    //**public static final String ACTION = "com.codepath.example.servicesdemo.alarm";

    // Triggered by the Alarm periodically (starts the service to run task)
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AutoSaveService.class);
        i.putExtra("name here", "some value");
        context.startService(i);
    }
}