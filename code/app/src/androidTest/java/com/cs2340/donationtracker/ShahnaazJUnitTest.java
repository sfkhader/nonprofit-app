package com.cs2340.donationtracker;

import android.support.annotation.NonNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.cs2340.donationtracker.activities.ItemSearchActivity;
import com.cs2340.donationtracker.activities.StartupActivity;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.action.ViewActions;
import android.support.test.runner.AndroidJUnit4;

import com.cs2340.donationtracker.model.FirebaseDonationDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class ShahnaazJUnitTest {

    @Rule
    public ActivityTestRule<ItemSearchActivity> mActivityRule =
            new ActivityTestRule<>(ItemSearchActivity.class);

    @Test
    public void searchAllByName_isCorrect() {
        Espresso.onView(ViewMatchers.withId(R.id.searchText)).perform(ViewActions.typeText("Name"));
        Espresso.onView(ViewMatchers.withId(R.id.bSearch)).perform(ViewActions.click());



    }
}