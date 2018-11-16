package com.cs2340.donationtracker;
import android.content.Intent;
import android.support.annotation.NonNull;

import org.junit.Test;
import com.cs2340.donationtracker.activities.ItemSearchResultsActivity;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


/**
 * The method this JUnit is testing takes in the search parameters entered by the user
 * and checks if they are all valid inputs. if valid, it returns true. if any of the search
 * parameters are false, it returns false.
 */
public class TahirahUnitTest {
    /**
     * this test uses VALID parameters
     */
    @Test
    public void setupSearch_isCorrect(){
        ItemSearchResultsActivity newInst = new ItemSearchResultsActivity();
        String loco = "AFD";
        String itemo = "WAHH";
        String texto = "Waluigi";

        assertTrue(newInst.setupSearch(loco, itemo, texto));
        String nullLoc = null;
        String nullItem = null;
        String nullText = null;
        assertFalse(newInst.setupSearch(nullLoc, nullItem, nullText));
        assertFalse(newInst.setupSearch(nullLoc, itemo, texto));
        assertFalse(newInst.setupSearch(loco, nullItem, nullText));

    }
}
