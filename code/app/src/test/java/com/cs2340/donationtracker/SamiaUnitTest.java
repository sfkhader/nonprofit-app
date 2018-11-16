package com.cs2340.donationtracker;

import com.cs2340.donationtracker.model.Donation;
import com.cs2340.donationtracker.model.DonationManager;
import com.cs2340.donationtracker.model.FirebaseDonationDatabase;

import net.bytebuddy.pool.TypePool;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * JUNIT to test getDonationNames()
 */
public class SamiaUnitTest {
    DonationManager donationManager;

    @Mock
    FirebaseDonationDatabase databaseMock;

    @Before
    public void whenCalledVerfied() {
         myList = mock(MyList.class);
        doNothing().when(myList).add(isA(Integer.class), isA(String.class));
        myList.add(0, "");

        verify(myList, times(1)).add(0, "");
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

    @Test(expected = IllegalArgumentException.class)
    public void testNullParameter() {
        donationManager.addDonation(null, null, null, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyString() {
        donationManager.addDonation("", "", "", "", "", "", "");
    }
}
