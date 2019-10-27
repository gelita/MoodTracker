package com.fanikiosoftware.moodtracker.controller;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.fanikiosoftware.moodtracker.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.fanikiosoftware.moodtracker.R.id.etComment;
import static org.hamcrest.Matchers.allOf;

@LargeTest
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private String testString;

    @Before
    public void initValidString() {
//      specify a valid string
        testString = "Espresso Test";
    }

    @Test
    public void mainActivityTest() {
        onView(withId(R.id.btnAdd))
                .perform(click());

//      Type text and then press the button.
        onView(withId(etComment))
                .perform(typeText(testString), closeSoftKeyboard());
        onView(allOf(withId(R.id.etComment))).check
                (matches(withText(testString)));
    }
}