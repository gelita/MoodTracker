package com.fanikiosoftware.moodtracker;

import com.fanikiosoftware.moodtracker.Model.ModelClass;
import com.fanikiosoftware.moodtracker.Utility.Constants;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * local unit test, which will execute on the development machine (host).
 */
public class MoodTrackerUnitTest {

    private static final String testString = "Test string!";

    @Test
    public void saveMood() {
        ModelClass modelClass = new ModelClass(0, testString);
        assertEquals(0, modelClass.getMoodId());
        assertEquals(testString, modelClass.getMemo());
    }
    @Test
    public void saveMood1() {
        ModelClass modelClass = new ModelClass(1, testString);
        assertEquals(1, modelClass.getMoodId());
        assertEquals(testString, modelClass.getMemo());
    }

    @Test
    public void saveMood2() {
        ModelClass modelClass = new ModelClass(2, testString);
        assertEquals(2, modelClass.getMoodId());
        assertEquals(testString, modelClass.getMemo());
    }

    @Test
    public void saveMood3() {
        ModelClass modelClass = new ModelClass(3, testString);
        assertEquals(3, modelClass.getMoodId());
        assertEquals(testString, modelClass.getMemo());
    }

    @Test
    public void saveMood4() {
        ModelClass modelClass = new ModelClass(4, testString);
        assertEquals(4, modelClass.getMoodId());
        assertEquals(testString, modelClass.getMemo());
    }

    @Test
    public void checkSelectedMoodId(){
        int selectedMoodId = Constants.MOOD_GREAT;
        assertEquals(0, selectedMoodId );
    }
    @Test
    public void checkSelectedMoodId1(){
        int selectedMoodId = Constants.MOOD_GOOD;
        assertEquals(1,selectedMoodId );
    }
    @Test
    public void checkSelectedMoodId2(){
        int selectedMoodId = Constants.MOOD_DECENT;
        assertEquals(2,selectedMoodId );
    }
    @Test
    public void checkSelectedMoodId3(){
        int selectedMoodId = Constants.MOOD_BAD;
        assertEquals(3,selectedMoodId );
    }
    @Test
    public void checkSelectedMoodId4(){
        int selectedMoodId = Constants.MOOD_SAD;
        assertEquals(4,selectedMoodId );
    }

//    @Test
//    public void checkTitles(){
//        int selectedMoodId = Constants.MOOD_GREAT;
//        assertEquals(0,selectedMoodId );
//    }
}