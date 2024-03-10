import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionBuilder {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/chankyahotel"; // URL to connect to the database
    private static final String USERNAME = "root"; // Username for database access
    private static final String PASSWORD = "1234"; // Password for database access

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Step 1: Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish a connection to the database
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            
            // Step 3: Check if connection is successful
            if (connection != null) {
                System.out.println("Connected to the database successfully!"); // Printing success message
                // You can perform database operations here
            } else {
                System.out.println("Failed to connect to the database!"); // Printing failure message
            }
        } catch (ClassNotFoundException e) {
            // Handle ClassNotFoundException (MySQL JDBC Driver not found)
            System.out.println("Error: MySQL JDBC Driver not found!"); // Printing driver not found error
            e.printStackTrace();
        } catch (SQLException e) {
            // Handle SQLException (Connection Failed)
            System.out.println("Error: Unable to connect to the database."); // Printing connection failed error
            e.printStackTrace();
        } finally {
            // Step 4: Close the connection in the finally block
            try {
                if (connection != null) {
                    connection.close(); // Close the database connection
                }
            } catch (SQLException e) {
                // Handle SQLException (Unable to close the connection)
                System.out.println("Error: Unable to close the connection."); // Print error while closing connection
                e.printStackTrace();
            }
        }
    }
}
