package com.cs2340.donationtracker;

import com.cs2340.donationtracker.model.Donation;
import com.cs2340.donationtracker.model.DonationManager;
import com.cs2340.donationtracker.model.FirebaseDonationDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class StephenUnitTest {

    DonationManager donationManager;

    @Mock
    FirebaseDonationDatabase databaseMock;

    @Before
    public void setUp() {
         donationManager = new DonationManager(databaseMock);
         when(databaseMock.getDonation("bread")).thenReturn(new Donation("bread",
                 "nice bread",
                 "freshly made bread",
                 "$9904",
                 "baked goods",
                 "12:04",
                 "AFD Station 4"));

         when(databaseMock.getDonation("banana")).thenReturn(null);
    }

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void testItemExists()  {
        Donation d = donationManager.getDonation("bread");
        assertEquals("bread", d.getName());
        assertEquals("nice bread", d.getShortDescription());
        assertEquals("freshly made bread", d.getFullDescription());
        assertEquals("$9904", d.getValue());
        assertEquals("baked goods", d.getCategory());
        assertEquals("12:04", d.getTimeStamp());
        assertEquals("AFD Station 4", d.getLocation());
    }

    @Test
    public void testItemMissing() {
        assertNull(donationManager.getDonation("banana"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullParameter() {
        donationManager.getDonation(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyString() {
        donationManager.getDonation("");
    }
}
