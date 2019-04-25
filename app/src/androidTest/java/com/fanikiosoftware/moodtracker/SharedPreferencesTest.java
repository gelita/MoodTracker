package com.fanikiosoftware.moodtracker;

import android.content.Context;
import android.content.SharedPreferences;

import com.fanikiosoftware.moodtracker.Utility.Constants;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import androidx.test.filters.SmallTest;
import androidx.test.platform.app.InstrumentationRegistry;
import static org.junit.Assert.assertEquals;

/**
 * Tests that modify the shared preferences.
 */
@SmallTest
public class SharedPreferencesTest {
    private SharedPreferences sharedPreferences;

    @Before
    public void before() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        sharedPreferences = context.getSharedPreferences(Constants.PREF_KEY_CURRENT_MEMO, Context.MODE_PRIVATE);
    }

    @Test
    public void put_and_get_preference() {
        String string1 = "my test memo";

        sharedPreferences.edit().putString(Constants.PREF_KEY_CURRENT_MEMO, string1).apply();
        String string2 = sharedPreferences.getString(Constants.PREF_KEY_CURRENT_MEMO, "");

        // Verify that the received data is correct.
        assertEquals(string1, string2);
    }

    @After
    public void after() {
        sharedPreferences.edit().putString(Constants.PREF_KEY_CURRENT_MEMO, null).apply();
    }
}