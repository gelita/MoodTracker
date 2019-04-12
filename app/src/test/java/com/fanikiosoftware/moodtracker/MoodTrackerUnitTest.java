package com.fanikiosoftware.moodtracker;

import com.fanikiosoftware.moodtracker.Model.ModelClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * local unit test, which will execute on the development machine (host).
 */
public class MoodTrackerUnitTest {
    @Test
    public void saveModel() {
        ModelClass modelClass = new ModelClass(1, "Today was a good day! It's payday!");
        assertEquals(1, modelClass.getMoodId());
        assertEquals("Today was a good day! It's payday!", modelClass.getMemo());
    }
}