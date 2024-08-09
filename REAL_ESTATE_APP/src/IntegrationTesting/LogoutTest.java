package IntegrationTesting;

import static org.junit.Assert.*;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import real_estate_app.Main_Menu;
import real_estate_app_users.Login;
import real_estate_app_property.propertyWindow;
import real_estate_app_property.searchProperty;

import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.Field;

public class LogoutTest {

    private Login loginFrame;
    private Main_Menu mainMenuFrame;
    private propertyWindow propertyWindowFrame;
    private searchProperty searchPropertyFrame;

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
        SwingUtilities.invokeAndWait(() -> {
            if (loginFrame != null) loginFrame.dispose();
            if (mainMenuFrame != null) mainMenuFrame.dispose();
            if (propertyWindowFrame != null) propertyWindowFrame.frame.dispose();
            if (searchPropertyFrame != null) searchPropertyFrame.dispose();
        });
    }

    @Test
    public void testLogoutFromMainMenu() throws Exception {
        // Initialize the main menu frame
        SwingUtilities.invokeAndWait(() -> {
            mainMenuFrame = new Main_Menu("testUser");
            mainMenuFrame.setVisible(true);
        });

        // Use reflection to access the private fields in Main_Menu
        JButton btnLogout = findButtonByText(mainMenuFrame.getContentPane(), "Log Out");

        assertNotNull("Cannot find the button 'Log Out'", btnLogout);

        // Simulate clicking the logout button
        SwingUtilities.invokeAndWait(() -> btnLogout.doClick());

        // Verify that the frame is disposed
        assertFalse(mainMenuFrame.isDisplayable());
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
