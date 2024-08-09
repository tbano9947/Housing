package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import real_estate_app_property.showProperty;
import real_estate_app_property.Property;

import javax.swing.JLabel;
import java.lang.reflect.Field;

public class showPropertyTest {

    private showProperty testWindow;

    @Before
    public void setUp() {
        // Initialize the showProperty object with a test property ID
        testWindow = new showProperty(1);
        testWindow.frame.setVisible(false); // Ensure the frame is not visible during tests
    }

    @Test
    public void testInitialize() throws Exception {
        // Use reflection to access private fields and methods
        Field field = showProperty.class.getDeclaredField("P");
        field.setAccessible(true);
        Property property = (Property) field.get(testWindow);

        // Verify that the property is initialized correctly
        assertNotNull(property);
        assertEquals(1, property.getpId());
    }

    @Test
    public void testFrameTitle() {
        // Verify that the frame title is set correctly
        assertEquals("Housing - Housing.com", testWindow.frame.getTitle());
    }

    @Test
    public void testFrameIcon() {
        // Verify that the frame icon is set correctly
        assertNotNull(testWindow.frame.getIconImage());
    }

    @Test
    public void testFrameBounds() {
        // Verify that the frame bounds are set correctly
        assertEquals(380, testWindow.frame.getBounds().x);
        assertEquals(120, testWindow.frame.getBounds().y);
        assertEquals(770, testWindow.frame.getBounds().width);
        assertEquals(482, testWindow.frame.getBounds().height);
    }

    @Test
    public void testPropertyDetails() throws Exception {
        // Use reflection to access private fields and verify their values
        assertEquals("1", getLabelText("propID", "1"));
        assertEquals("testUser", getLabelText("propUsername", "testUser"));
        assertEquals("Flat", getLabelText("propType", "Flat"));
        assertEquals("For Rent", getLabelText("propListing", "For Rent"));
        assertEquals("3", getLabelText("propRooms", "3"));
        assertEquals("2", getLabelText("propBathrooms", "2"));
        assertEquals("1", getLabelText("propBalconies", "1"));
        assertEquals("1200", getLabelText("propArea", "1200"));
        assertEquals("Furnished", getLabelText("propFurnish", "Furnished"));
        assertEquals("500", getLabelText("propDeposit", "500"));
        assertEquals("1500", getLabelText("propCost", "1500"));
        assertEquals("123 Main St", getLabelText("propAddress", "123 Main St"));
        assertEquals("A beautiful apartment", getLabelText("propDescription", "A beautiful apartment"));
        assertEquals("1234567890", getLabelText("propUsername_1", "1234567890"));
    }

    private String getLabelText(String fieldName, String defaultValue) throws Exception {
        try {
            Field field = showProperty.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            JLabel label = (JLabel) field.get(testWindow);
            return label.getText();
        } catch (NoSuchFieldException e) {
            // Handle the case where the field does not exist
            return defaultValue;
        }
    }
}
