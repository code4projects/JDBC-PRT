import java.sql.*;

// DatabaseManager class to manage database connection
class DBManager {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/hotelmanagement";
    private static final String USER = "root";
    private static final String PASS = "1234";
    private Connection conn;

    // Step 1: Constructor to initialize the database connection
    public DBManager() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Step 9: Method to close the database connection
    public void closeConnection() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Step 2: Method to get the database connection
    public Connection getConnection() {
        return conn;
    }
}

// SalaryUpdater class to update employee salaries
class SalaryUpdater {
    private Connection conn;

    // Step 3: Constructor to initialize the SalaryUpdater with a database connection
    public SalaryUpdater(Connection conn) {
        this.conn = conn;
    }

    // Step 6: Method to update the salary of an employee
    public void updateSalary(int employeeId, double newSalary) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE Employees SET salary = " + newSalary + " WHERE id = " + employeeId;
            stmt.executeUpdate(sql);
            System.out.println("Salary updated successfully.");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// Main class to orchestrate the process
public class Main {
    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();

        SalaryUpdater salaryUpdater = new SalaryUpdater(conn);
        salaryUpdater.updateSalary(1, 50000); // Step 7: Update salary for employee with id = 1

        dbManager.closeConnection(); // Step 8: Close the database connection
    }
}
