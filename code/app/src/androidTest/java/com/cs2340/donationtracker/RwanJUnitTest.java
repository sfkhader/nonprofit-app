package com.cs2340.donationtracker;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.cs2340.donationtracker.activities.ItemSearchActivity;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.action.ViewActions;
import android.support.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class RwanJUnitTest {
    @Rule
    public ActivityTestRule<ItemSearchActivity> mActivityRule =
            new ActivityTestRule<>(ItemSearchActivity.class);

    @Test
    public void searchByCategory_isCorrect() {
        Espresso.onView(ViewMatchers.withId(R.id.searchText)).perform(
                ViewActions.typeText("Category"));
        Espresso.onView(ViewMatchers.withId(R.id.bSearch)).perform(ViewActions.click());
    }
}
