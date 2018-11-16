package com.cs2340.donationtracker;
import com.cs2340.donationtracker.model.Donation;
import com.cs2340.donationtracker.model.FirebaseDonationDatabase;
import com.cs2340.donationtracker.model.Location;
import com.cs2340.donationtracker.model.DonationManager;
import com.cs2340.donationtracker.model.LocationManager;
import com.cs2340.donationtracker.model.FirebaseLocationDatabase;

import net.bytebuddy.pool.TypePool;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class RwanUnitTest {
    DonationManager donationManager;

    @Mock
    FirebaseDonationDatabase databaseMock;

    @Before
    public void setUp() {
        donationManager = new DonationManager(databaseMock);
        ArrayList<String> donationList = new ArrayList<>();
        donationList.add("nothing");
        when(donationManager.searchByCategory("goodstuff")).thenReturn(donationList);

        when(donationManager.searchByCategory("badstuff")).thenReturn(null);
    }

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void testItemExists()  {
        Collection<String> donationList = new ArrayList<>();
        donationList.add("nothing");
        List d = donationManager.searchByCategory("goodstuff");
        assertEquals(donationList, d);
    }

    @Test
    public void testItemMissing() {
        assertNull(donationManager.searchByCategory("badstuff"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullParameter() {
        donationManager.searchByCategory(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyString() {
        donationManager.searchByCategory("");
    }
}
