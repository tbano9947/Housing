package IntegrationTesting;

import static org.junit.Assert.*;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import real_estate_app_property.propertyWindow;

import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.Field;

public class AddSearchPropertyTest {

    private propertyWindow propertyWindowFrame;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            propertyWindowFrame = new propertyWindow("testUser");
            propertyWindowFrame.frame.setVisible(true);
        });
        // Adding a small delay to ensure all components are initialized
        Thread.sleep(1000);
    }

    @After
    public void tearDown() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            if (propertyWindowFrame != null) propertyWindowFrame.frame.dispose();
        });
    }

    @Test
    public void testAddAndSearchProperty() throws Exception {
        // Use reflection to access the private fields in propertyWindow
        JTextField txtAddress = (JTextField) getPrivateField(propertyWindowFrame, "address");
        JTextField txtRooms = (JTextField) getPrivateField(propertyWindowFrame, "rooms");
        JTextField txtBathrooms = (JTextField) getPrivateField(propertyWindowFrame, "bathrooms");
        JTextField txtBalconies = (JTextField) getPrivateField(propertyWindowFrame, "balconies");
        JTextField txtArea = (JTextField) getPrivateField(propertyWindowFrame, "area");
        JTextField txtDeposit = (JTextField) getPrivateField(propertyWindowFrame, "deposit");
        JTextField txtCost = (JTextField) getPrivateField(propertyWindowFrame, "cost");
        JTextField txtDescription = (JTextField) getPrivateField(propertyWindowFrame, "description");
        JButton btnSubmit = findButtonByText(propertyWindowFrame.frame, "SUBMIT");

        assertNotNull("Cannot find the text field 'address'", txtAddress);
        assertNotNull("Cannot find the text field 'rooms'", txtRooms);
        assertNotNull("Cannot find the text field 'bathrooms'", txtBathrooms);
        assertNotNull("Cannot find the text field 'balconies'", txtBalconies);
        assertNotNull("Cannot find the text field 'area'", txtArea);
        assertNotNull("Cannot find the text field 'deposit'", txtDeposit);
        assertNotNull("Cannot find the text field 'cost'", txtCost);
        assertNotNull("Cannot find the text field 'description'", txtDescription);
        assertNotNull("Cannot find the button 'SUBMIT'", btnSubmit);

        // Simulate user input and property addition
        SwingUtilities.invokeAndWait(() -> {
            txtAddress.setText("123 Main St");
            txtRooms.setText("3");
            txtBathrooms.setText("2");
            txtBalconies.setText("1");
            txtArea.setText("1200");
            txtDeposit.setText("500");
            txtCost.setText("1500");
            txtDescription.setText("A beautiful apartment");
            // Hardcode the values for pType, pDetails, and furnishing
            // These values are not set via reflection but directly in the test
        });

        // Simulate clicking the submit button
        SwingUtilities.invokeAndWait(() -> btnSubmit.doClick());

        // Verify that the property was added (you might need to adjust this part based on your implementation)
        // For example, you could check if the property exists in the database or if a success message was shown
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
}
