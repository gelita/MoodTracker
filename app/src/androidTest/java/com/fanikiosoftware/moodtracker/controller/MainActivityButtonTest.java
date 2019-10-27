package com.fanikiosoftware.moodtracker.controller;

import com.fanikiosoftware.moodtracker.R;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
public class MainActivityButtonTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void HistoryButtonTest() {
        onView(withId(R.id.btnHistory)).check(matches((isDisplayed())));
   }

    @Test
    public void PieChartButtonTest() {
        onView(withId(R.id.btnChart)).check(matches((isDisplayed())));
    }

    @Test
    public void AddButtonTest() {
        onView(withId(R.id.btnAdd)).check(matches((isDisplayed())));
    }
}