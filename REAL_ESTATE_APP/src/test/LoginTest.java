package test;

import static org.junit.Assert.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import real_estate_app_users.Login;

public class LoginTest {

    private Login loginFrame;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            loginFrame = new Login();
            loginFrame.setVisible(true);
        });
        // Adding a small delay to ensure all components are initialized
        Thread.sleep(1000);
    }

    @After
    public void tearDown() throws Exception {
        SwingUtilities.invokeAndWait(() -> loginFrame.dispose());
    }

    @Test
    public void testLoginFrameTitle() {
        assertEquals("Housing - Housing.com", loginFrame.getTitle());
    }

    @Test
    public void testLabelsText() {
        JLabel lblHousing = findLabelByText(loginFrame, "Housing");
        assertNotNull("Cannot find the label 'Housing'", lblHousing);
        assertEquals("Housing", lblHousing.getText());

        JLabel lblLogin = findLabelByText(loginFrame, "LOGIN");
        assertNotNull("Cannot find the label 'LOGIN'", lblLogin);
        assertEquals("LOGIN", lblLogin.getText());

        JLabel lblUserID = findLabelByText(loginFrame, "User ID");
        assertNotNull("Cannot find the label 'User ID'", lblUserID);
        assertEquals("User ID", lblUserID.getText());

        JLabel lblPassword = findLabelByText(loginFrame, "Password");
        assertNotNull("Cannot find the label 'Password'", lblPassword);
        assertEquals("Password", lblPassword.getText());
    }

    @Test
    public void testTextFieldsExistence() {
        JTextField usernameField = findTextField(loginFrame);
        assertNotNull("Cannot find the username text field", usernameField);

        JPasswordField passwordField = findPasswordField(loginFrame);
        assertNotNull("Cannot find the password field", passwordField);
    }

    @Test
    public void testButtonsExistence() {
        JButton btnLogin = findButtonByText(loginFrame, "ENTER");
        assertNotNull("Cannot find the button 'ENTER'", btnLogin);

        JButton btnCancel = findButtonByText(loginFrame, "CANCEL");
        assertNotNull("Cannot find the button 'CANCEL'", btnCancel);
    }

    private JLabel findLabelByText(java.awt.Component parent, String text) {
        if (parent instanceof JLabel && text.equals(((JLabel) parent).getText())) {
            return (JLabel) parent;
        }
        if (parent instanceof java.awt.Container) {
            java.awt.Component[] components = ((java.awt.Container) parent).getComponents();
            for (java.awt.Component component : components) {
                JLabel found = findLabelByText(component, text);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    private JTextField findTextField(java.awt.Component parent) {
        if (parent instanceof JTextField) {
            return (JTextField) parent;
        }
        if (parent instanceof java.awt.Container) {
            java.awt.Component[] components = ((java.awt.Container) parent).getComponents();
            for (java.awt.Component component : components) {
                JTextField found = findTextField(component);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    private JPasswordField findPasswordField(java.awt.Component parent) {
        if (parent instanceof JPasswordField) {
            return (JPasswordField) parent;
        }
        if (parent instanceof java.awt.Container) {
            java.awt.Component[] components = ((java.awt.Container) parent).getComponents();
            for (java.awt.Component component : components) {
                JPasswordField found = findPasswordField(component);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    private JButton findButtonByText(java.awt.Component parent, String text) {
        if (parent instanceof JButton && text.equals(((JButton) parent).getText())) {
            return (JButton) parent;
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
