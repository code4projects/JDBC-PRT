import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TataDAO {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/chankyahotel";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    private Connection connection;

    // Step 1: Constructor to initialize the TataDAO object and establish the database connection
    public TataDAO() {
        try {
            // Registering the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establishing a connection to the database
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Step 2: Method to insert a new customer record into the database
    public void insertCustomer(String name, String email) {
        PreparedStatement preparedStatement = null;

        try {
            // SQL INSERT statement
            String sql = "INSERT INTO customers (name, email) VALUES (?, ?)";

            // Creating a PreparedStatement object for the INSERT statement
            preparedStatement = connection.prepareStatement(sql);

            // Setting parameter values for the PreparedStatement
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);

            // Executing the PreparedStatement to insert the new record
            int rowsAffected = preparedStatement.executeUpdate();

            // Checking if the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("New customer inserted successfully!");
            } else {
                System.out.println("Failed to insert new customer!");
            }
        } catch (SQLException e) {
            System.out.println("Database operation failed!");
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Step 3: Method to close the database connection
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method to demonstrate the usage of the TataDAO class
    public static void main(String[] args) {
        // Step 4: Creating an instance of TataDAO
        TataDAO tataDAO = new TataDAO();
        
        // Step 5: Inserting a new customer record
        tataDAO.insertCustomer("Suhaib Ahmad", "Suhaibahmadpeur@gmail.com");
        
        // Step 6: Closing the database connection
        tataDAO.closeConnection();
    }
}
