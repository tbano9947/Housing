package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import real_estate_app_property.Property;

public class PropertyTest {

    private Property testProperty;

    @Before
    public void setUp() {
        // Initialize the Property object with test data
        testProperty = new Property(
            "Apartment", 
            "Furnished", 
            "For Rent", 
            "123 Main St", 
            "A beautiful apartment", 
            "3", 
            "2", 
            "1", 
            "1200", 
            "500", 
            "1500", 
            "testUser"
        );
    }

    @Test
    public void testAddToDatabase() {
        // This test would ideally check if the property is added to the database
        // Since we can't interact with the actual database in a unit test, we can mock this behavior
        // For now, we'll just ensure no exceptions are thrown
        try {
            testProperty.addToDatabase();
        } catch (Exception e) {
            fail("Exception thrown during addToDatabase: " + e.getMessage());
        }
    }

    @Test
    public void testRetrieveFromDatabase() {
        // This test would ideally check if the property is retrieved from the database
        // Since we can't interact with the actual database in a unit test, we can mock this behavior
        // For now, we'll just ensure no exceptions are thrown
        try {
            int result = testProperty.retrieveFromDatabase(1);
            assertEquals(1, result);
        } catch (Exception e) {
            fail("Exception thrown during retrieveFromDatabase: " + e.getMessage());
        }
    }

    @Test
    public void testGetters() {
        // Test the getters to ensure they return the correct values
        assertEquals("Apartment", testProperty.getpType());
        assertEquals("Furnished", testProperty.getpFurnishedStatus());
        assertEquals("For Rent", testProperty.getpListing());
        assertEquals("123 Main St", testProperty.getpAddress());
        assertEquals("A beautiful apartment", testProperty.getpDescription());
        assertEquals("3", testProperty.getpRooms());
        assertEquals("2", testProperty.getpBathrooms());
        assertEquals("1", testProperty.getpBalconies());
        assertEquals("1200", testProperty.getpArea());
        assertEquals("500", testProperty.getpDeposit());
        assertEquals("1500", testProperty.getpCost());
        assertEquals("testUser", testProperty.getpUsername());
    }

    @Test
    public void testSetters() {
        // Test the setters to ensure they correctly update the values
        testProperty.setpType("House");
        assertEquals("House", testProperty.getpType());

        testProperty.setpFurnishedStatus("Unfurnished");
        assertEquals("Unfurnished", testProperty.getpFurnishedStatus());

        testProperty.setpListing("For Sale");
        assertEquals("For Sale", testProperty.getpListing());

        testProperty.setpAddress("456 Elm St");
        assertEquals("456 Elm St", testProperty.getpAddress());

        testProperty.setpDescription("A spacious house");
        assertEquals("A spacious house", testProperty.getpDescription());

        testProperty.setpRooms("4");
        assertEquals("4", testProperty.getpRooms());

        testProperty.setpBathrooms("3");
        assertEquals("3", testProperty.getpBathrooms());

        testProperty.setpBalconies("2");
        assertEquals("2", testProperty.getpBalconies());

        testProperty.setpArea("2000");
        assertEquals("2000", testProperty.getpArea());

        testProperty.setpDeposit("1000");
        assertEquals("1000", testProperty.getpDeposit());

        testProperty.setpCost("3000");
        assertEquals("3000", testProperty.getpCost());

        testProperty.setpUsername("newUser");
        assertEquals("newUser", testProperty.getpUsername());
    }
}

