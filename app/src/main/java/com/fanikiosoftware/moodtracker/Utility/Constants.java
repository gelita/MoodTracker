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

    public static final int BANANA_YELLOW = -398257;
    public static final int LIGHT_SAGE = -4658810;
    public static final int CORNFLOWER_BLUE = -16537100;
    public static final int WARM_GREY = -6579301;
    public static final int FADED_RED = -2212784;

    public static final int[] colorsArr = {
            R.color.banana_yellow,
            R.color.light_sage,
            R.color.cornflower_blue,
            R.color.warm_grey,
            R.color.faded_red
    };

    //Prevent objects from being constructed from
    //this class, by declaring this private constructor.
    private Constants() {
        //this prevents even the native class from
        //calling this constructor as well
        throw new AssertionError();
    }
}
