package real_estate_app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class THE_CONNECTION {
	
	private static String jdbcURL = "jdbc:mysql://localhost:3306/property";
	private static String username = "root";
	private static String password = "";
	
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
		connection = DriverManager.getConnection(jdbcURL, username, password);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return connection;
	}
}
