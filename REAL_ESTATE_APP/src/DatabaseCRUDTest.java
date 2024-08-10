import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseCRUDTest {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/property";
    private static String username = "root";
    private static String password = "";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Measure the time taken to establish the connection
            long connectionStartTime = System.currentTimeMillis();
            connection = DriverManager.getConnection(jdbcURL, username, password);
            long connectionEndTime = System.currentTimeMillis();
            long connectionTime = connectionEndTime - connectionStartTime;
            System.out.println("Time taken to establish the connection: " + connectionTime + " milliseconds");

            // Create a statement
            statement = connection.createStatement();

            // Measure the time taken to perform the Create operation
            String createQuery = "INSERT INTO users (username, password, fname, lname, number, age, gender, usertype) VALUES ('JohnDoe', 'password123', 'John', 'Doe', '1234567890', '30', 'Male', 'Buyer')";
            long createStartTime = System.currentTimeMillis();
            statement.executeUpdate(createQuery);
            long createEndTime = System.currentTimeMillis();
            long createTime = createEndTime - createStartTime;
            System.out.println("Time taken to perform the Create operation: " + createTime + " milliseconds");

            // Measure the time taken to perform the Read operation
            String readQuery = "SELECT * FROM users";
            long readStartTime = System.currentTimeMillis();
            resultSet = statement.executeQuery(readQuery);
            long readEndTime = System.currentTimeMillis();
            long readTime = readEndTime - readStartTime;
            System.out.println("Time taken to perform the Read operation: " + readTime + " milliseconds");

            // Process the result set
            while (resultSet.next()) {
                System.out.println("User ID: " + resultSet.getInt("user_id"));
                System.out.println("User Name: " + resultSet.getString("username"));
                // Add more columns as needed
            }

            // Measure the time taken to perform the Update operation
            String updateQuery = "UPDATE users SET number = '0987654321' WHERE username = 'JohnDoe'";
            long updateStartTime = System.currentTimeMillis();
            statement.executeUpdate(updateQuery);
            long updateEndTime = System.currentTimeMillis();
            long updateTime = updateEndTime - updateStartTime;
            System.out.println("Time taken to perform the Update operation: " + updateTime + " milliseconds");

            // Measure the time taken to perform the Delete operation
            String deleteQuery = "DELETE FROM users WHERE username = 'JohnDoe'";
            long deleteStartTime = System.currentTimeMillis();
            statement.executeUpdate(deleteQuery);
            long deleteEndTime = System.currentTimeMillis();
            long deleteTime = deleteEndTime - deleteStartTime;
            System.out.println("Time taken to perform the Delete operation: " + deleteTime + " milliseconds");

            // Calculate the total time taken
            long totalTime = connectionTime + createTime + readTime + updateTime + deleteTime;
            System.out.println("Total time taken: " + totalTime + " milliseconds");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
