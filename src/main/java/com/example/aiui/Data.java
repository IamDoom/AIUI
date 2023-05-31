package com.example.aiui;
import java.sql.*;

public class Data {

    String dbDriver = "com.mysql.cj.jdbc.Driver";
    String dbUrl = "jdbc:mysql://localhost:3307/employees";
    String dbUsername = "root";
    String dbPassword = "";

    public boolean registerUser(String firstname, String lastname, String emailaddress, String username, String password, boolean administrator){
        User newEmployee = new User(firstname, lastname, emailaddress, username, password, administrator);
        try {
            // Load the driver
            Class.forName(dbDriver);
            // Establish the connection
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            // Create an employee
            String insertQuery = "INSERT INTO employees (firstname, lastname, emailaddress, username, password, employeeID, administrator) VALUES (?, ?, ?, ?, ?, ?, ?)";

            // Prepare the statement with the employee details
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, newEmployee.getFirstName());
            statement.setString(2, newEmployee.getLastName());
            statement.setString(3, newEmployee.getEmail());
            statement.setString(4, newEmployee.getUsername());
            statement.setString(5, newEmployee.getPassword());
            statement.setInt(6, newEmployee.getEmployeeID());
            statement.setBoolean(7, newEmployee.isAdministrator());

            // Execute the insert statement
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected+" "+newEmployee.getFirstName()+" "+newEmployee.getLastName()+": "+newEmployee.getEmployeeID()+" is succesvol aangemaakt");

            // Close the statement and connection
            statement.close();
            connection.close();
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load the database driver.");
            e.getMessage();
            return false;
        } catch (SQLException e) {
            System.out.println("Failed to create an employee.");
            e.getMessage();
            return false;
        }
    }

    public User retrieveEmployee(User testEmployee) {

        return testEmployee;
    }
    public User login(String username, String password) {

        try {
            Class.forName(dbDriver);

            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            String query = "SELECT * FROM employees WHERE username = ? AND password = ?";
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
            } else {
                System.out.println("No matching user found for the given credentials.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load the database driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to execute the login query.");
            e.printStackTrace();
        }
        System.out.println("User login failed");
        return null;
    }

    public  void createChatDB(){
        try {
            // Load the driver
            Class.forName(dbDriver);

            // Establish the connection
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            // Create a new database
            Statement statement = connection.createStatement();
            String databaseName = "chats";
            String createDatabaseQuery = "CREATE DATABASE " + databaseName;
            statement.executeUpdate(createDatabaseQuery);
            System.out.println("Database created successfully!");

            // Switch to the new database
            statement.execute("USE " + databaseName);

            // Create a new table
            String createTableQuery = "CREATE TABLE chats (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "username VARCHAR(50) NOT NULL," +
                    "message VARCHAR(250) NOT NULL," +
                    ")";
            statement.executeUpdate(createTableQuery);
            System.out.println("Table created successfully!");

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load the chatdatabase driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to create the chatdatabase or table.");
            e.printStackTrace();
        }
    }

    public  void createDB(){
        try {
            // Load the driver
            Class.forName(dbDriver);

            // Establish the connection
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            // Create a new database
            Statement statement = connection.createStatement();
            String databaseName = "employees";
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
                    "emailaddress VARCHAR(100) NOT NULL," +
                    "username VARCHAR(50) NOT NULL UNIQUE,"+
                    "password VARCHAR(50) NOT NULL," +
                    "employeeID INT NOT NULL UNIQUE," +
                    "administrator BOOLEAN NOT NULL," +
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
}
