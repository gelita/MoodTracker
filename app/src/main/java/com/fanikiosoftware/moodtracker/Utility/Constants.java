package com.fanikiosoftware.moodtracker.Utility;

import com.fanikiosoftware.moodtracker.R;

public final class Constants {

    public static final String PREF_KEY_CURRENT_MOOD = "PREFERENCE_KEY_CURRENT_MOOD";
    public static final String PREF_KEY_MOOD1 = "PREFERENCE_KEY_MOOD1";
    public static final String PREF_KEY_MOOD2 = "PREFERENCE_KEY_MOOD2";
    public static final String PREF_KEY_MOOD3 = "PREFERENCE_KEY_MOOD3";
    public static final String PREF_KEY_MOOD4 = "PREFERENCE_KEY_MOOD4";
    public static final String PREF_KEY_MOOD5 = "PREFERENCE_KEY_MOOD5";
    public static final String PREF_KEY_MOOD6 = "PREFERENCE_KEY_MOOD6";
    public static final String PREF_KEY_MOOD7 = "PREFERENCE_KEY_MOOD7";

    public static final String PREF_KEY_CURRENT_MEMO = "PREFERENCE_KEY_CURRENT_MEMO";
    public static final String PREF_KEY_MEMO1 = "PREFERENCE_KEY_MEMO1";
    public static final String PREF_KEY_MEMO2 = "PREFERENCE_KEY_MEMO2";
    public static final String PREF_KEY_MEMO3 = "PREFERENCE_KEY_MEMO3";
    public static final String PREF_KEY_MEMO4 = "PREFERENCE_KEY_MEMO4";
    public static final String PREF_KEY_MEMO5 = "PREFERENCE_KEY_MEMO5";
    public static final String PREF_KEY_MEMO6 = "PREFERENCE_KEY_MEMO6";
    public static final String PREF_KEY_MEMO7 = "PREFERENCE_KEY_MEMO7";

    public static final String[] colorsArr = {
            "#fff9ec4f",
            "#ffb8e986",
            "#03A9F4",
            "#ff9b9b9b",
            "#ffde3c50",
            "#d3d3d3"
    };
    public static final int[] emojiArr = {
            R.drawable.great_mood,
            R.drawable.good_mood,
            R.drawable.decent_mood,
            R.drawable.bad_mood,
            R.drawable.sad_mood
    };
    public static final int MOOD_GREAT = 0;
    public static final int MOOD_GOOD = 1;
    public static final int MOOD_DECENT = 2;
    public static final int MOOD_BAD = 3;
    public static final int MOOD_SAD = 4;
    public static final String[] moods = {
            "great",
            "good",
            "decent",
            "bad",
            "sad"
    };

    public static final String[] titles = {
            "One week ago",
            "6 days ago",
            "5 days ago",
            "4 days ago",
            "3 days ago",
            "2 days ago",
            "1 day ago"
    };

    //Prevent objects from being constructed from
    //this class, by declaring this private constructor.
    private Constants() {
        //this prevents even the native class from
        //calling this constructor as well
        throw new AssertionError();
    }
}