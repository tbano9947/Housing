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

import real_estate_app_users.User_Registration;

import java.lang.reflect.Field;

public class UserRegistrationTest {

    private User_Registration registrationFrame;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            registrationFrame = new User_Registration();
            registrationFrame.setVisible(true);
        });
        // Adding a small delay to ensure all components are initialized
        Thread.sleep(1000);
    }

    @After
    public void tearDown() throws Exception {
        SwingUtilities.invokeAndWait(() -> registrationFrame.dispose());
    }

    @Test
    public void testVerifyFields() throws Exception {
        setTextFieldValue(registrationFrame, "user_name", "testUser");
        setTextFieldValue(registrationFrame, "first_name", "John");
        setTextFieldValue(registrationFrame, "last_name", "Doe");
        setTextFieldValue(registrationFrame, "contact_num", "1234567890");
        setTextFieldValue(registrationFrame, "age_num", "30");
        setPasswordFieldValue(registrationFrame, "pwd", "testPass");

        assertTrue("Fields verification failed", registrationFrame.verifyFields());
    }

    @Test
    public void testLabelsExistence() {
        JLabel lblUserRegistration = findLabelByText(registrationFrame, "USER REGISTRATION");
        assertNotNull("Cannot find the label 'USER REGISTRATION'", lblUserRegistration);

        JLabel lblUsername = findLabelByText(registrationFrame, "Username");
        assertNotNull("Cannot find the label 'Username'", lblUsername);

        JLabel lblFirstName = findLabelByText(registrationFrame, "First Name");
        assertNotNull("Cannot find the label 'First Name'", lblFirstName);

        JLabel lblLastName = findLabelByText(registrationFrame, "Last Name");
        assertNotNull("Cannot find the label 'Last Name'", lblLastName);

        JLabel lblContactNo = findLabelByText(registrationFrame, "Contact No.");
        assertNotNull("Cannot find the label 'Contact No.'", lblContactNo);

        JLabel lblAge = findLabelByText(registrationFrame, "Age");
        assertNotNull("Cannot find the label 'Age'", lblAge);

        JLabel lblGender = findLabelByText(registrationFrame, "Gender");
        assertNotNull("Cannot find the label 'Gender'", lblGender);

        JLabel lblUserType = findLabelByText(registrationFrame, "User Type");
        assertNotNull("Cannot find the label 'User Type'", lblUserType);

        JLabel lblPassword = findLabelByText(registrationFrame, "Password");
        assertNotNull("Cannot find the label 'Password'", lblPassword);
    }

    @Test
    public void testTextFieldsExistence() {
        JTextField userNameField = findTextField(registrationFrame);
        assertNotNull("Cannot find the username text field", userNameField);

        JTextField firstNameField = findTextField(registrationFrame);
        assertNotNull("Cannot find the first name text field", firstNameField);

        JTextField lastNameField = findTextField(registrationFrame);
        assertNotNull("Cannot find the last name text field", lastNameField);

        JTextField contactNumField = findTextField(registrationFrame);
        assertNotNull("Cannot find the contact number text field", contactNumField);

        JTextField ageNumField = findTextField(registrationFrame);
        assertNotNull("Cannot find the age text field", ageNumField);

        JPasswordField passwordField = findPasswordField(registrationFrame);
        assertNotNull("Cannot find the password field", passwordField);
    }

    @Test
    public void testButtonsExistence() {
        JButton registerButton = findButtonByText(registrationFrame, "REGISTER");
        assertNotNull("Cannot find the button 'REGISTER'", registerButton);

        JButton cancelButton = findButtonByText(registrationFrame, "CANCEL");
        assertNotNull("Cannot find the button 'CANCEL'", cancelButton);
    }

    private void setTextFieldValue(Object obj, String fieldName, String value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        JTextField textField = (JTextField) field.get(obj);
        textField.setText(value);
    }

    private void setPasswordFieldValue(Object obj, String fieldName, String value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        JPasswordField passwordField = (JPasswordField) field.get(obj);
        passwordField.setText(value);
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
