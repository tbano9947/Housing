package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import real_estate_app_property.deleteProperty;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class deletePropertyTest {

    private deleteProperty testWindow;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        // Set up the actual database connection
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/property", "root", "");

        // Create a test table and insert test data
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS property_list_test (id INT, pType VARCHAR(255), pListing VARCHAR(255), pAddress VARCHAR(255), pCost VARCHAR(255), username VARCHAR(255))");
        statement.execute("TRUNCATE TABLE property_list_test");
        statement.execute("INSERT INTO property_list_test (id, pType, pListing, pAddress, pCost, username) VALUES (1, 'Flat', 'For Rent', '123 Main St', '1500', 'testUser')");

        // Initialize the deleteProperty object with a test username
        SwingUtilities.invokeAndWait(() -> {
            testWindow = new deleteProperty("testUser") {
                @Override
                public void initialize(String s) {
                    super.initialize(s);
                    try {
                        Field tableField = deleteProperty.class.getDeclaredField("table");
                        tableField.setAccessible(true);
                        JTable table = (JTable) tableField.get(this);
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.addRow(new Object[]{1, "Flat", "For Rent", "123 Main St", "1500"});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            testWindow.frame.setVisible(true);
        });

        // Adding a small delay to ensure all components are initialized
        Thread.sleep(1000);
    }

    @Test
    public void testFrameTitle() {
        assertEquals("DELETE PROPERTY DIALOG BOX", testWindow.frame.getTitle());
    }

    @Test
    public void testTableModel() throws Exception {
        JTable table = getPrivateField(testWindow, "table");
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        assertEquals(5, model.getColumnCount());
        assertEquals("Property ID", model.getColumnName(0));
        assertEquals("Type", model.getColumnName(1));
        assertEquals("Listing", model.getColumnName(2));
        assertEquals("Address", model.getColumnName(3));
        assertEquals("Rent / Cost", model.getColumnName(4));
    }

    @Test
    public void testDeleteButtonAction() throws Exception {
        JButton deleteButton = getPrivateField(testWindow, "btnNewButton");
        ActionListener[] listeners = deleteButton.getActionListeners();
        assertTrue(listeners.length > 0);
        for (ActionListener listener : listeners) {
            listener.actionPerformed(new ActionEvent(deleteButton, ActionEvent.ACTION_PERFORMED, null));
        }
    }

    @Test
    public void testCancelButtonAction() throws Exception {
        JButton cancelButton = getPrivateField(testWindow, "btnCancel");
        ActionListener[] listeners = cancelButton.getActionListeners();
        assertTrue(listeners.length > 0);
        for (ActionListener listener : listeners) {
            listener.actionPerformed(new ActionEvent(cancelButton, ActionEvent.ACTION_PERFORMED, null));
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T getPrivateField(Object object, String fieldName) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(object);
    }

    // Utility method to print all declared fields of the deleteProperty class
    public static void printDeclaredFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

    public static void main(String[] args) {
        printDeclaredFields(deleteProperty.class);
    }
}
