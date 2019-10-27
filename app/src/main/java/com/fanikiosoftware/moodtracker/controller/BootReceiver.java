package com.fanikiosoftware.moodtracker.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.fanikiosoftware.moodtracker.utility.MyCalendarClass;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
//        verify that intent rec'd is in fact the one that is registered in the manifest
        assert action != null;
        if(action.equals("android.intent.action.BOOT_COMPLETED")){
            MyCalendarClass.setAlarm(context);
        }
    }
}