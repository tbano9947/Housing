package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import real_estate_app_property.searchProperty;
import real_estate_app_property.Property;

import javax.swing.JTextField;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class searchPropertyTest {

    private searchProperty testWindow;

    @Before
    public void setUp() {
        // Initialize the searchProperty object
        testWindow = new searchProperty();
        testWindow.setVisible(false); // Ensure the frame is not visible during tests
    }

    @Test
    public void testVerifyFields() throws Exception {
        // Set up the text fields with test data using reflection
        setTextField("propRoom", "3");
        setTextField("propBath", "2");

        // Verify that the fields are correctly verified
        assertTrue(testWindow.verifyFields());

        // Test with an empty field
        setTextField("propRoom", "");
        assertFalse(testWindow.verifyFields());
    }

    @Test
    public void testVerifyValidity() {
        // Assuming the database is set up correctly, this test will check the validity of the property
        boolean isValid = testWindow.verifyValidity("Flat", "Furnished", "3", "2");
        
        // Adjust this based on your actual database content
        // If you expect the property to exist, use assertFalse
        // If you expect the property not to exist, use assertTrue
        assertTrue(isValid); // or assertFalse(isValid) based on your database content
    }

    @Test
    public void testListProperty() {
        // Assuming the database is set up correctly, this test will check the listing of properties
        ArrayList<Property> properties = testWindow.ListProperty("Flat", "Furnished", "3", "2");
        assertNotNull(properties);
        // Further assertions can be added based on the expected properties
    }

    @Test
    public void testFindProperties() throws Exception {
        // Set up the text fields and combo boxes with test data using reflection
        setTextField("propRoom", "3");
        setTextField("propBath", "2");
        setComboBox("propType", "Flat");
        setComboBox("propCondition", "Furnished");

        // Call the findProperties method
        testWindow.findProperties();

        // Verify that the table model is set correctly
        assertNotNull(getField("table").get(testWindow));
        assertEquals(8, ((javax.swing.JTable) getField("table").get(testWindow)).getModel().getColumnCount());
    }

    private void setTextField(String fieldName, String value) throws Exception {
        Field field = searchProperty.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        JTextField textField = (JTextField) field.get(testWindow);
        textField.setText(value);
    }

    private void setComboBox(String fieldName, String value) throws Exception {
        Field field = searchProperty.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        javax.swing.JComboBox comboBox = (javax.swing.JComboBox) field.get(testWindow);
        comboBox.setSelectedItem(value);
    }

    private Field getField(String fieldName) throws Exception {
        Field field = searchProperty.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }
}
