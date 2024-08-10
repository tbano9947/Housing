package PerformanceTesting;

import static org.junit.Assert.*;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import real_estate_app.Home_Screen;
import real_estate_app_users.Login;

import java.lang.reflect.Field;

public class LoginPerformanceTest {

    private Home_Screen homeScreen;
    private Login loginFrame;

    // Test data for multiple users
    private Object[][] testData = {
        {"tasnim", "4321", "bano", "tasnim", "9876543210", 25, "Female", "Buyer"},
        {"ashish", "12345", "ashish", "kumar", "8865432100", 24, "Male", "Seller"},
        {"Harshil", "12345", "Harshil", "Patel", "8907654321", 23, "Male", "Seller"},
        {"Harsh", "1234567", "Harsh", "Patel", "8765432100", 22, "Male", "Buyer"},
        {"Ramandeep", "12345678", "Ramandeep", "kaur", "9876543210", 23, "Female", "Seller"},
        {"testuser", "password", "Test", "User", "1234567890", 30, "Male", "Buyer"},
        {"tanim", "123", "tanim", "bano", "8765432900", 21, "Female", "Seller"},
        {"Raman", "321", "Raman", "Deep", "1234567890", 23, "Female", "Buyer"},
        {"Manpreet", "321", "Manpreet", "kaur", "2212014020", 23, "Female", "Buyer"}
    };

    private long totalTimeTaken = 0;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            homeScreen = new Home_Screen();
            homeScreen.setVisible(true);
        });
        // Adding a small delay to ensure all components are initialized
        Thread.sleep(1000);
    }

    @After
    public void tearDown() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            if (homeScreen != null) homeScreen.dispose();
            if (loginFrame != null) loginFrame.dispose();
        });
    }

    @Test
    public void testMultipleLogins() throws Exception {
        for (Object[] userData : testData) {
            String username = (String) userData[0];
            String password = (String) userData[1];
            performLoginAndMeasureTime(username, password);
        }

        // Print the total time taken for all logins
        System.out.println("Total time taken for all login attempts: " + totalTimeTaken + " milliseconds");
    }

    private void performLoginAndMeasureTime(String username, String password) throws Exception {
        // Find and click the login button on the home screen
        JButton btnLogin = findButtonByText(homeScreen, "Log In\r\n");
        assertNotNull("Cannot find the button 'Log In\r\n'", btnLogin);

        SwingUtilities.invokeAndWait(() -> btnLogin.doClick());

        // Simulate the delay for the login frame to appear
        Thread.sleep(1000);

        // Initialize the login frame
        SwingUtilities.invokeAndWait(() -> {
            loginFrame = new Login();
            loginFrame.setVisible(true);
        });

        // Use reflection to access the private fields
        JTextField txtUsername = (JTextField) getPrivateField(loginFrame, "jTextField_Username");
        JPasswordField txtPassword = (JPasswordField) getPrivateField(loginFrame, "jpasswordField1");
        JButton btnLoginSubmit = findButtonByText(loginFrame, "ENTER");

        assertNotNull("Cannot find the text field 'jTextField_Username'", txtUsername);
        assertNotNull("Cannot find the password field 'jpasswordField1'", txtPassword);
        assertNotNull("Cannot find the button 'ENTER'", btnLoginSubmit);

        // Measure time before login attempt
        long startTime = System.currentTimeMillis();

        // Simulate user input and login
        SwingUtilities.invokeAndWait(() -> {
            txtUsername.setText(username);
            txtPassword.setText(password);
            btnLoginSubmit.doClick();
        });

        // Measure time after login attempt
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;

        // Accumulate the time taken
        totalTimeTaken += timeTaken;

        System.out.println("Time taken for login with username '" + username + "': " + timeTaken + " milliseconds");
    }

    private Object getPrivateField(Object object, String fieldName) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    private JButton findButtonByText(java.awt.Component parent, String text) {
        if (parent instanceof JButton) {
            JButton button = (JButton) parent;
            if (text.equals(button.getText())) {
                return button;
            }
        }
        if (parent instanceof java.awt.Container) {
            java.awt.Component[] components = ((java.awt.Container) parent).getComponents();
            for (java.awt.Component component : components) {
                JButton found = findButtonByText(component, text);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
}
