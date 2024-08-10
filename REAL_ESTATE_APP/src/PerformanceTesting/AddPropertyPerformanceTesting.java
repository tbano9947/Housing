package PerformanceTesting;

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

public class AddPropertyPerformanceTesting {

    private propertyWindow propertyWindowFrame;
    private long totalTimeTaken = 0;

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
    public void testAdd50Properties() throws Exception {
        for (int i = 1; i <= 50; i++) {
            String address = "Property " + i + " Address";
            String rooms = String.valueOf(2 + i % 5);  // varying number of rooms
            String bathrooms = String.valueOf(1 + i % 3);  // varying number of bathrooms
            String balconies = String.valueOf(i % 2);  // varying number of balconies
            String area = String.valueOf(1000 + i * 20);  // varying area size
            String deposit = String.valueOf(500 + i * 10);  // varying deposit
            String cost = String.valueOf(1500 + i * 50);  // varying cost
            String description = "Description for Property " + i;

            performAddPropertyAndMeasureTime(address, rooms, bathrooms, balconies, area, deposit, cost, description);
        }

        // Print the total time taken for adding all 50 properties
        System.out.println("Total time taken for adding 50 properties: " + totalTimeTaken + " milliseconds");
    }

    private void performAddPropertyAndMeasureTime(String address, String rooms, String bathrooms, String balconies, 
                                                  String area, String deposit, String cost, String description) throws Exception {
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

        // Measure time before adding the property
        long startTime = System.currentTimeMillis();

        // Simulate user input and property addition
        SwingUtilities.invokeAndWait(() -> {
            txtAddress.setText(address);
            txtRooms.setText(rooms);
            txtBathrooms.setText(bathrooms);
            txtBalconies.setText(balconies);
            txtArea.setText(area);
            txtDeposit.setText(deposit);
            txtCost.setText(cost);
            txtDescription.setText(description);
            btnSubmit.doClick();
        });

        // Measure time after adding the property
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;

        // Accumulate the time taken
        totalTimeTaken += timeTaken;

        System.out.println("Time taken for adding property '" + address + "': " + timeTaken + " milliseconds");
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

