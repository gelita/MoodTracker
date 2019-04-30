package com.fanikiosoftware.moodtracker;

import com.fanikiosoftware.moodtracker.Model.ModelClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * local unit test, which will execute on the development machine (host).
 */
public class MoodTrackerUnitTest {

    private static final String string1 = "Today was a good day! It's payday!";

    @Test
    public void saveMood1() {
        ModelClass modelClass = new ModelClass(1, string1);
        assertEquals(1, modelClass.getMoodId());
        assertEquals(string1, modelClass.getMemo());
    }

    @Test
    public void saveMood2() {
        ModelClass modelClass = new ModelClass(2, string1);
        assertEquals(2, modelClass.getMoodId());
        assertEquals(string1, modelClass.getMemo());
    }

    @Test
    public void saveMood3() {
        ModelClass modelClass = new ModelClass(3, string1);
        assertEquals(3, modelClass.getMoodId());
        assertEquals(string1, modelClass.getMemo());
    }

    @Test
    public void saveMood4() {
        ModelClass modelClass = new ModelClass(4, string1);
        assertEquals(4, modelClass.getMoodId());
        assertEquals(string1, modelClass.getMemo());
    }

    @Test
    public void saveMood5() {
        ModelClass modelClass = new ModelClass(5, string1);
        assertEquals(5, modelClass.getMoodId());
        assertEquals(string1, modelClass.getMemo());
    }
}