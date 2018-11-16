package com.cs2340.donationtracker;
import com.cs2340.donationtracker.model.FirebaseDonationDatabase;
import com.cs2340.donationtracker.model.DonationManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * JUnit for searchByName method for Donations
 */
public class SamiaUnitTest {
    DonationManager donationManager;

    @Mock
    FirebaseDonationDatabase databaseMock;

    @Before
    public void setUp() {
        donationManager = new DonationManager(databaseMock);
        ArrayList<String> donations = new ArrayList<>();
        donations.add("apple");
        when(donationManager.searchByName("apple")).thenReturn(donations);

        when(donationManager.searchByName("orange")).thenReturn(null);
    }

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void testItemExists()  {
        Collection<String> donations = new ArrayList<>();
        donations.add("apple");
        List d = donationManager.searchByName("apple");
        assertEquals(donations, d);
    }

    @Test
    public void testItemMissing() {
        assertNull(donationManager.searchByName("orange"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullParameter() {
        donationManager.searchByName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyString() {
        donationManager.searchByName("");
    }
}
