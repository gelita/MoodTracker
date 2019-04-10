package com.fanikiosoftware.moodtracker.Controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.fanikiosoftware.moodtracker.Utility.MyCalendarClass;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
//        verify that intent rec'd is in fact the one that is registered in the manifest
        if(action.equals("android.intent.action.BOOT_COMPLETED")){
            MyCalendarClass.setAlarm(context);
            System.out.println("BootReceiver:: BOOT_COMPLETED Broadcast rec'd, setting alarm");
        }
    }
}