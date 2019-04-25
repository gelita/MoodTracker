package com.fanikiosoftware.moodtracker;

import com.fanikiosoftware.moodtracker.Controller.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTesting {

    public static final String TYPED_STRING = "I'm testing the save memo method.";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
//        click on add button and then type in text
        Espresso.onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.etComment))
                .perform(typeText(TYPED_STRING), closeSoftKeyboard());
        onView(withId(R.id.btnConfirm))
                .perform(click());
        onView(withId(R.id.etComment))
                .check(matches(withText(TYPED_STRING)));
    }
}