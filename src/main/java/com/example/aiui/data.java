package com.example.aiui;


import java.sql.*;

class User{
    private String firstName;
    private String lastName;
    private String password;
    private String Username;
    private int employeeID;
    private String Email;
    private boolean administrator;

    public User(String firstName, String lastName, String password, String username, int employeeID, String email, boolean administrator) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        Username = username;
        this.employeeID = employeeID;
        Email = email;
        this.administrator = administrator;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


}
public class data {
    public User retrieveEmployee(User testEmployee) {
        return testEmployee;
    }

    public void createTestEmployee() {
        createEmployee("john", "doe", "johndoe@emailadress.com","username", "securepassword", 234045, true);

    }
    public User login(String username, String password) {

        try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3307/employees";
        String DBusername = "root";
        String DBpassword = "";
        Connection connection = DriverManager.getConnection(url, DBusername, DBpassword);
        String query = "SELECT COUNT(*) FROM employees WHERE emailaddress = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultset = statement.executeQuery();
        if (resultset.next()) {
            String firstName = resultset.getString("firstname");
            String lastName = resultset.getString("lastname");
            String email = resultset.getString("emailaddress");
            int employeeID = resultset.getInt("employeeID");
            boolean administrator = resultset.getBoolean("administrator");
            User user = new User(firstName, lastName, password, username, employeeID, email, administrator);
            return user;
        }

    } catch(ClassNotFoundException e)

    {
        System.out.println("Failed to load the database driver.");
        e.printStackTrace();
    } catch(SQLException e){
        System.out.println("Failed to check if the user exists.");
        e.printStackTrace();
    }
        System.out.println("user login failed");
        return null;
}

    public void createEmployee(String firstName, String lastName, String emailAddress, String userName ,String password,
                                      int employeeID, boolean administrator) {
        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Define connection parameters
            String url = "jdbc:mysql://localhost:3307/employees"; // Replace "mydatabase" with the name of your database
            String DBusername = "root";
            String DBpassword = "";

            // Establish the connection
            Connection connection = DriverManager.getConnection(url, DBusername, DBpassword);

            // Create an employee
            String insertQuery = "INSERT INTO employees (firstname, lastname, emailaddress, username, password, employeeID, administrator) VALUES (?, ?, ?, ?, ?, ?, ?)";

            // Prepare the statement with the employee details
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, emailAddress);
            statement.setString(4, userName);
            statement.setString(5, password);
            statement.setInt(6, employeeID);
            statement.setBoolean(7, administrator);

            // Execute the insert statement
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted successfully!");

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load the database driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to create an employee.");
            e.printStackTrace();
        }
    }

    public  void createDB(){
        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Define connection parameters
            String url = "jdbc:mysql://localhost:3307/mysql";
            String username = "root";
            String password = "";

            // Establish the connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create a new database
            Statement statement = connection.createStatement();
            String databaseName = "employees"; // Replace with your desired database name
            String createDatabaseQuery = "CREATE DATABASE " + databaseName;
            statement.executeUpdate(createDatabaseQuery);
            System.out.println("Database created successfully!");

            // Switch to the new database
            statement.execute("USE " + databaseName);

            // Create a new table
            String createTableQuery = "CREATE TABLE employees (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "firstname VARCHAR(50) NOT NULL," +
                    "lastname VARCHAR(50) NOT NULL," +
                    "username VARCHAR(50) NOT NULL UNIQUE"+
                    "emailaddress VARCHAR(100) NOT NULL," +
                    "password VARCHAR(50) NOT NULL," +
                    "employeeID INT NOT NULL UNIQUE," +
                    "administrator BOOLEAN NOT NULL" +
                    ")";
            statement.executeUpdate(createTableQuery);
            System.out.println("Table created successfully!");

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load the database driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to create the database or table.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
