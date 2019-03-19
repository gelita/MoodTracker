package com.fanikiosoftware.moodtracker;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Angela Rosas on 3/12/2019.
 */
public class AutoSaveService extends IntentService {
    public AutoSaveService() {
        super("MyTestService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Do the task here
        Log.i("MyAutoSaveService", "Service running");
    }
}