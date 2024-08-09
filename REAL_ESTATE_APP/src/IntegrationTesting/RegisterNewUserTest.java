package IntegrationTesting;

import static org.junit.Assert.*;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import real_estate_app.Home_Screen;
import real_estate_app_users.User_Registration;

import java.lang.reflect.Field;

public class RegisterNewUserTest {

    private Home_Screen homeScreen;
    private User_Registration registrationFrame;

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
            if (registrationFrame != null) registrationFrame.dispose();
        });
    }

    @Test
    public void testNavigationAndRegistration() throws Exception {
        // Find and click the register button on the home screen
        JButton btnRegister = findButtonByText(homeScreen, "Register as New User");
        assertNotNull("Cannot find the button 'Register as New User'", btnRegister);

        SwingUtilities.invokeAndWait(() -> btnRegister.doClick());

        // Simulate the delay for the registration frame to appear
        Thread.sleep(1000);

        // Initialize the registration frame
        SwingUtilities.invokeAndWait(() -> {
            registrationFrame = new User_Registration();
            registrationFrame.setVisible(true);
        });

        // Use reflection to access the private fields
        JTextField txtUsername = (JTextField) getPrivateField(registrationFrame, "user_name");
        JTextField txtFirstName = (JTextField) getPrivateField(registrationFrame, "first_name");
        JTextField txtLastName = (JTextField) getPrivateField(registrationFrame, "last_name");
        JTextField txtContactNum = (JTextField) getPrivateField(registrationFrame, "contact_num");
        JTextField txtAgeNum = (JTextField) getPrivateField(registrationFrame, "age_num");
        JPasswordField txtPassword = (JPasswordField) getPrivateField(registrationFrame, "pwd");
        JButton btnSubmit = findButtonByText(registrationFrame, "REGISTER");

        assertNotNull("Cannot find the text field 'user_name'", txtUsername);
        assertNotNull("Cannot find the text field 'first_name'", txtFirstName);
        assertNotNull("Cannot find the text field 'last_name'", txtLastName);
        assertNotNull("Cannot find the text field 'contact_num'", txtContactNum);
        assertNotNull("Cannot find the text field 'age_num'", txtAgeNum);
        assertNotNull("Cannot find the password field 'pwd'", txtPassword);
        assertNotNull("Cannot find the button 'REGISTER'", btnSubmit);

        // Simulate user input and registration
        SwingUtilities.invokeAndWait(() -> {
            txtUsername.setText("testuser");
            txtFirstName.setText("Test");
            txtLastName.setText("User");
            txtContactNum.setText("1234567890");
            txtAgeNum.setText("30");
            txtPassword.setText("password");
            btnSubmit.doClick();
        });

        // Add assertions to verify the registration functionality
        // For example, check if a success message is displayed
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

