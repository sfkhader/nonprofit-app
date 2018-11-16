package com.cs2340.donationtracker;

import com.cs2340.donationtracker.model.Donation;
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

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class ShahnaazUnitTest1 {
    LocationManager locationManager;

    @Mock
    FirebaseLocationDatabase databaseMock;

    @Before
    public void setUp() {
        locationManager = new LocationManager(databaseMock);
        when(locationManager.getLocation(5)).thenReturn(new Location("nice location",
                "5",
                "5",
                "123 nice street",
                "nice city",
                "nice state",
                "nice zip",
                "dropoff location",
                "1231231234",
                "www.nicelocation.com"));
        when(locationManager.getLocation(-1)).thenReturn(null);
        when(locationManager.getLocation(10)).thenReturn(null);
    }

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testItemsThatExist()  {
        Location l = locationManager.getLocation(5);
        assertEquals("nice location", l.getName());
        assertEquals("5", l.getLatitude());
        assertEquals("5", l.getLongitude());
        assertEquals("123 nice street", l.getStreetAddress());
        assertEquals("nice city", l.getCity());
        assertEquals("nice state", l.getState());
        assertEquals("nice zip", l.getZip());
        assertEquals("dropoff location", l.getType());
        assertEquals("1231231234", l.getPhone());
        assertEquals("www.nicelocation.com", l.getWebsite());
    }

    @Test
    public void testItemThatDoesNotExist() {
        Location l = locationManager.getLocation(10);
        assertNull(l);

    }

    @Test
    public void testLocationIDOutOfBounds() {
        assertNull(locationManager.getLocation(-1));
    }

}
