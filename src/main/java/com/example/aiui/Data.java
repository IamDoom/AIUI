package com.example.aiui;

import java.sql.*;

public class Data {

    // Registreren van een gebruiker
    public boolean registerUser(String firstname, String lastname, String emailaddress, String username, String password, boolean administrator){
        return true;
    }

    // Gegevens ophalen van een medewerker
    public User retrieveEmployee(User testEmployee) {
        return testEmployee;
    }

    // Voor het testen: maak een testmedewerker aan
    public void createTestEmployee() {
        createEmployee("john", "doe", "emailadres@voorbeeld.com","testgebruiker", "wachtwoord", 234045, true);
    }

    // Inloggen van een gebruiker
    public User login(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3307/employees"; // Verander "employees" naar de naam van jouw database
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
                System.out.println("Geen overeenkomende gebruiker gevonden voor de opgegeven inloggegevens.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Kon de database driver niet laden.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Fout bij het uitvoeren van de login-query.");
            e.printStackTrace();
        }
        System.out.println("Inloggen mislukt");
        return null;
    }

    // Een medewerker aanmaken
    public void createEmployee(String firstName, String lastName, String emailAddress, String username, String password,
                               int employeeID, boolean administrator) {
        try {
            // Laden van de database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Definieer de verbindingsparameters
            String url = "jdbc:mysql://localhost:3307/employees"; // Verander "employees" naar de naam van jouw database
            String DBusername = "root";
            String DBpassword = "";

            // Verbinding maken met de database
            Connection connection = DriverManager.getConnection(url, DBusername, DBpassword);

            // Een medewerker aanmaken
            String insertQuery = "INSERT INTO employees (firstname, lastname, emailaddress, username, password, employeeID, administrator) VALUES (?, ?, ?, ?, ?, ?, ?)";

            // Voorbereiden van de statement met de medewerkersgegevens
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, emailAddress);
            statement.setString(4, username);
            statement.setString(5, password);
            statement.setInt(6, employeeID);
            statement.setBoolean(7, administrator);

            // Uitvoeren van de insert statement
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " rij(en) succesvol toegevoegd!");

            // Sluiten van de statement en verbinding
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Kon de database driver niet laden.");
            e.getMessage();
        } catch (SQLException e) {
            System.out.println("Fout bij het aanmaken van een medewerker.");
            e.getMessage();
        }
    }

    // Een database en tabel aanmaken
    public void createDB(){
        try {
            // Laden van de database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Definieer de verbindingsparameters
            String url = "jdbc:mysql://localhost:3307/mysql";
            String username = "root";
            String password = "";

            // Verbinding maken met de database
            Connection connection = DriverManager.getConnection(url, username, password);

            // Een nieuwe database aanmaken
            Statement statement = connection.createStatement();
            String databaseName = "employees"; // Verander naar de gewenste naam van je database
            String createDatabaseQuery = "CREATE DATABASE " + databaseName;
            statement.executeUpdate(createDatabaseQuery);
            System.out.println("Database succesvol aangemaakt!");

            // Schakelen naar de nieuwe database
            statement.execute("USE " + databaseName);

            // Een nieuwe tabel aanmaken
            String createTableQuery = "CREATE TABLE employees (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "firstname VARCHAR(50) NOT NULL," +
                    "lastname VARCHAR(50) NOT NULL," +
                    "emailaddress VARCHAR(100) NOT NULL," +
                    "username VARCHAR(50) NOT NULL UNIQUE," +
                    "password VARCHAR(50) NOT NULL," +
                    "employeeID INT NOT NULL UNIQUE," +
                    "administrator BOOLEAN NOT NULL" +
                    ")";
            statement.executeUpdate(createTableQuery);
            System.out.println("Tabel succesvol aangemaakt!");

            // Sluiten van de statement en verbinding
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Kon de database driver niet laden.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Fout bij het aanmaken van de database of tabel.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
