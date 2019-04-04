package com.fanikiosoftware.moodtracker.Utility;

import com.fanikiosoftware.moodtracker.R;

public final class Constants {



    public static final String PREF_KEY_MOOD1 = "PREFERENCE_KEY_MOOD1";
    public static final String PREF_KEY_MOOD2 = "PREFERENCE_KEY_MOOD2";
    public static final String PREF_KEY_MOOD3 = "PREFERENCE_KEY_MOOD3";
    public static final String PREF_KEY_MOOD4 = "PREFERENCE_KEY_MOOD4";
    public static final String PREF_KEY_MOOD5 = "PREFERENCE_KEY_MOOD5";
    public static final String PREF_KEY_MOOD6 = "PREFERENCE_KEY_MOOD6";
    public static final String PREF_KEY_MOOD7 = "PREFERENCE_KEY_MOOD7";

    public static final String PREF_KEY_MEMO1 = "PREFERENCE_KEY_MEMO1";
    public static final String PREF_KEY_MEMO2 = "PREFERENCE_KEY_MEMO2";
    public static final String PREF_KEY_MEMO3 = "PREFERENCE_KEY_MEMO3";
    public static final String PREF_KEY_MEMO4 = "PREFERENCE_KEY_MEMO4";
    public static final String PREF_KEY_MEMO5 = "PREFERENCE_KEY_MEMO5";
    public static final String PREF_KEY_MEMO6 = "PREFERENCE_KEY_MEMO6";
    public static final String PREF_KEY_MEMO7 = "PREFERENCE_KEY_MEMO7";

    public static final String[] PREF_MOODS_ARR = {
            PREF_KEY_MOOD1,
            PREF_KEY_MOOD2,
            PREF_KEY_MOOD3,
            PREF_KEY_MOOD4,
            PREF_KEY_MOOD5,
            PREF_KEY_MOOD6,
            PREF_KEY_MOOD7
    };

    public static final String[] PREF_MEMOS_ARR = {
            PREF_KEY_MEMO1,
            PREF_KEY_MEMO2,
            PREF_KEY_MEMO3,
            PREF_KEY_MEMO4,
            PREF_KEY_MEMO5,
            PREF_KEY_MEMO6,
            PREF_KEY_MEMO7
    };

    public static final int[] colorsArr = {
            R.color.banana_yellow,
            R.color.light_sage,
            R.color.cornflower_blue,
            R.color.warm_grey,
            R.color.faded_red
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


    public static final int MOOD_GREAT = 0;
    public static final int MOOD_GOOD = 1;
    public static final int MOOD_DECENT = 2;
    public static final int MOOD_SAD = 3;
    public static final int MOOD_BAD = 4;

    public static final int[] MOODS = {MOOD_GREAT, MOOD_GOOD, MOOD_DECENT, MOOD_SAD, MOOD_BAD};

    //Prevent objects from being constructed from
    //this class, by declaring this private constructor.
    private Constants() {
        //this prevents even the native class from
        //calling this constructor as well
        throw new AssertionError();
    }
}
