package com.example.aiui;


import java.sql.*;

class User{
    private static int IDcounter = 1;
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

    public User(String firstName, String lastName, String password, String username, String email, boolean administrator) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        Username = username;
        IDcounter += 1;
        this.employeeID = IDcounter;
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
    public boolean registerUser(String firstname, String lastname, String emailaddress, String username, String password, boolean administrator){
        User newEmployee = new User(firstname, lastname, emailaddress, username, password, administrator);
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

    public void createTestEmployee() {
        createEmployee("john", "doe", "johndoe@emailadress.com","testusername", "securepassword", 234045, true);

    }
    public User login(String username, String password) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3307/employees";
            String DBusername = "root";
            String DBpassword = "";
            Connection connection = DriverManager.getConnection(url, DBusername, DBpassword);
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
            Class.forName(dbClass);

            // Establish the connection
            Connection connection = DriverManager.getConnection(employeesUrl, dbUsername, dbPassword);

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
            System.out.println("Failed to load the database driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to create the database or table.");
            e.printStackTrace();
        }
    }

    public  void createDB(){
        try {
            // Load the driver
            Class.forName(dbClass);

            // Establish the connection
            Connection connection = DriverManager.getConnection(employeesUrl, dbUsername, dbPassword);

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
