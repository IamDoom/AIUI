package com.example.aiui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.Objects;


public class loginController implements Observer {
    Data DB = new Data();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Pane Base;
    @FXML
    private Button login;
    @FXML
    private Button mode;
    @FXML
    private Button button;
    @FXML
    private Pane leftPane;
    @FXML
    private Pane rightPane;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private Label errorMessage;
    public loginController(){

    }

    /**
     * Methode om tussen lichte en donkere modus te schakelen
     */


    /**
     * Methode om in te loggen na het indrukken van de login knop
     * @param event Het actie-evenement
     */
    @FXML
    protected void submitlogin(ActionEvent event) {
        String enteredPassword = password.getText();
        String enteredUsername = username.getText();

        if (enteredPassword.isEmpty() || enteredUsername.isEmpty()) {
            errorMessage.setText("Wachtwoord of gebruikersnaam incompleet");
            System.out.println("Wachtwoord of gebruikersnaam incompleet");
            System.out.println(enteredUsername);
            System.out.println(enteredPassword);
        } else {
            User user = DB.login(enteredUsername, enteredPassword);
            System.out.println(enteredUsername);
            System.out.println(enteredPassword);

            if (user == null) {
                errorMessage.setText("Wachtwoord of gebruikersnaam incorrect");
                System.out.println("Wachtwoord of gebruikersnaam incorrect");
            } else {
                try {
                    System.out.println("Login succesvol");
                    login(event);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            }
        }
    }

    /**
     * Methode om de lichte modus in te schakelen
     */
    protected void lightMode() {
        username.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        password.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        leftPane.setStyle("-fx-background-color: #5bc3f0;");
        rightPane.setStyle("-fx-background-color: #174694;");
        mode.setText("Donkere modus");
        mode.setStyle("-fx-background-color: #5BC3F0");
        button.setStyle("-fx-background-color: #5BC3F0");
    }

    /**
     * Methode om de donkere modus in te schakelen
     */
    protected void darkMode() {
        username.setStyle("-fx-background-color: #1b8bc5; -fx-text-fill: white;");
        password.setStyle("-fx-background-color: #1b8bc5; -fx-text-fill: white;");
        leftPane.setStyle("-fx-background-color: #1b8bc5;");
        rightPane.setStyle("-fx-background-color: #174694;");
        mode.setText("Lichte modus");
        mode.setStyle("-fx-background-color: #1b8bc5");
        button.setStyle("-fx-background-color: #1b8bc5");
    }
    protected void color1(){

    }
    protected void color2(){

    }


    @Override
    public void update(boolean darkmode, boolean lightmode, boolean colormode1, boolean colormode2) {

        if (darkmode == true){
            darkMode();
        } else if (lightmode == true) {
            lightMode();
        } else if (colormode1 == true) {
            color1();
        } else if (colormode2 == true) {
            color2();
        }


    }

    /**
     * Methode om in te loggen en de hoofdscene te laden
     * @param event Het actie-evenement
     * @throws IOException Als er een fout optreedt bij het laden van de fxml-bestanden
     */
    @FXML
    public void login(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
