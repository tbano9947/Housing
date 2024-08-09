package IntegrationTesting;

import static org.junit.Assert.*;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import real_estate_app_property.searchProperty;

import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class searchIntegrationTest {

    private searchProperty searchPropertyFrame;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            searchPropertyFrame = new searchProperty();
            searchPropertyFrame.setVisible(true);
        });
        // Adding a small delay to ensure all components are initialized
        Thread.sleep(1000);
    }

    @After
    public void tearDown() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            if (searchPropertyFrame != null) searchPropertyFrame.dispose();
        });
    }

    @Test
    public void testSearchProperty() throws Exception {
        // Use reflection to access the private fields in searchProperty
        JTextField txtRooms = (JTextField) getPrivateField(searchPropertyFrame, "propRoom");
        JTextField txtBathrooms = (JTextField) getPrivateField(searchPropertyFrame, "propBath");
        JComboBox<String> cmbType = (JComboBox<String>) getPrivateField(searchPropertyFrame, "propType");
        JComboBox<String> cmbCondition = (JComboBox<String>) getPrivateField(searchPropertyFrame, "propCondition");
        JButton btnSearch = findButtonByText(searchPropertyFrame.getContentPane(), "SEARCH");

        assertNotNull("Cannot find the text field 'propRoom'", txtRooms);
        assertNotNull("Cannot find the text field 'propBath'", txtBathrooms);
        assertNotNull("Cannot find the combo box 'propType'", cmbType);
        assertNotNull("Cannot find the combo box 'propCondition'", cmbCondition);
        assertNotNull("Cannot find the button 'SEARCH'", btnSearch);

        // Simulate user input for searching properties
        SwingUtilities.invokeAndWait(() -> {
            txtRooms.setText("3");
            txtBathrooms.setText("2");
            cmbType.setSelectedItem("Flat");
            cmbCondition.setSelectedItem("Furnished");
        });

        // Simulate clicking the search button
        SwingUtilities.invokeAndWait(() -> btnSearch.doClick());

        // Verify that the search results are displayed correctly
        assertNotNull(getField(searchPropertyFrame, "table").get(searchPropertyFrame));
        assertEquals(8, ((javax.swing.JTable) getField(searchPropertyFrame, "table").get(searchPropertyFrame)).getModel().getColumnCount());
    }

    private Object getPrivateField(Object object, String fieldName) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    private JButton findButtonByText(Container container, String text) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals(text)) {
                    return button;
                }
            } else if (component instanceof Container) {
                JButton button = findButtonByText((Container) component, text);
                if (button != null) {
                    return button;
                }
            }
        }
        return null;
    }

    private Field getField(Object object, String fieldName) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }
}

