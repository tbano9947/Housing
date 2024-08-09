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
import real_estate_app_users.Login;

import java.lang.reflect.Field;

public class HomeScreenIntegrationTest {

    private Home_Screen homeScreen;
    private Login loginFrame;

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
    public void testNavigationAndLogin() throws Exception {
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

        // Simulate user input and login
        SwingUtilities.invokeAndWait(() -> {
            txtUsername.setText("testuser");
            txtPassword.setText("password");
            btnLoginSubmit.doClick();
        });

        // Add assertions to verify the login functionality
        // For example, check if a new frame is opened or a message is displayed
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
