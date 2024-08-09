package test;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import real_estate_app_property.propertyWindow;

import javax.swing.JTextField;

public class propertyWindowTest {

    private propertyWindow testWindow;

    @Before
    public void setUp() {
        // Initialize the propertyWindow object with test data
        testWindow = new propertyWindow("testUser");
        testWindow.frame.setVisible(false); // Ensure the frame is not visible during tests
    }

    @Test
    public void testVerifyFields() {
        // Set up the text fields with test data
        setTextField(testWindow.address, "123 Main St");
        setTextField(testWindow.rooms, "3");
        setTextField(testWindow.bathrooms, "2");
        setTextField(testWindow.balconies, "1");
        setTextField(testWindow.area, "1200");
        setTextField(testWindow.deposit, "500");
        setTextField(testWindow.cost, "1500");
        setTextField(testWindow.description, "A beautiful apartment");

        // Verify that the fields are correctly verified
        assertTrue(testWindow.verifyFields());

        // Test with an empty field
        setTextField(testWindow.address, "");
        assertFalse(testWindow.verifyFields());
    }

    @Test
    public void testClose() {
        // Ensure no exceptions are thrown when closing the window
        try {
            testWindow.close();
        } catch (Exception e) {
            fail("Exception thrown during close: " + e.getMessage());
        }
    }

    private void setTextField(JTextField textField, String value) {
        textField.setText(value);
    }
}

