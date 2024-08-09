package test;

import static org.junit.Assert.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import real_estate_app.Home_Screen;

public class HomeScreenTest {

    private Home_Screen homeScreen;

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
        SwingUtilities.invokeAndWait(() -> homeScreen.dispose());
    }

    @Test
    public void testHomeScreenTitle() {
        assertEquals("Housing - Housing.com", homeScreen.getTitle());
    }

    @Test
    public void testLabelsText() {
        JLabel lblHousing = findLabelByText(homeScreen, "Housing");
        assertNotNull("Cannot find the label 'Housing'", lblHousing);
        assertEquals("Housing", lblHousing.getText());

        JLabel lblWelcome = findLabelByText(homeScreen, "WELCOME !");
        assertNotNull("Cannot find the label 'WELCOME !'", lblWelcome);
        assertEquals("WELCOME !", lblWelcome.getText());
    }

    @Test
    public void testButtonsExistence() {
        JButton btnRegister = findButtonByText(homeScreen, "Register as New User");
        assertNotNull("Cannot find the button 'Register as New User'", btnRegister);

        JButton btnLogin = findButtonByText(homeScreen, "Log In\r\n");
        assertNotNull("Cannot find the button 'Log In\r\n'", btnLogin);
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

    private JButton findButtonByText(java.awt.Component parent, String text) {
        if (parent instanceof JButton) {
            JButton button = (JButton) parent;
            System.out.println("Found button with text: " + button.getText());
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

    private boolean isFrameVisible(String frameTitle) {
        for (java.awt.Frame frame : java.awt.Frame.getFrames()) {
            System.out.println("Frame title: " + frame.getTitle() + ", visible: " + frame.isVisible());
            if (frame.isVisible() && frame.getTitle().equals(frameTitle)) {
                return true;
            }
        }
        return false;
    }
}
