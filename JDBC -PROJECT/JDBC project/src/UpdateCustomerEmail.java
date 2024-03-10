import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCustomerEmail {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hotelmanagement";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Step 1: Registering the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establishing a connection to the database
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Step 3: SQL UPDATE statement
            String sql = "UPDATE customers SET email = ? WHERE id = ?";

            // Step 4: Creating a PreparedStatement object for the UPDATE statement
            preparedStatement = connection.prepareStatement(sql);

            // Step 5: Setting parameter values for the PreparedStatement
            preparedStatement.setString(1, "new_suhaibahmadpeur@.com");
            preparedStatement.setInt(2, 123); // Assuming 123 is the customer's ID

            // Step 6: Executing the PreparedStatement to update the customer's email address
            int rowsAffected = preparedStatement.executeUpdate();

            // Step 7: Checking the result of the update operation
            if (rowsAffected > 0) {
                System.out.println("Customer email updated successfully!");
            } else {
                System.out.println("Failed to update customer email!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database operation failed!");
            e.printStackTrace();
        } finally {
            try {
                // Step 8: Closing PreparedStatement and Connection in the finally block
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
