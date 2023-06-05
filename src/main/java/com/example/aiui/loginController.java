package com.example.aiui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class loginController {
    Data DB = new Data();

    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean lightMode = true;

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

    @FXML
    protected void Toggle() {
        if (lightMode) {
            lightMode = false;
            darkMode();
        } else {
            lightMode = true;
            lightMode();
        }
    }

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

    protected void lightMode() {
        username.setStyle("-fx-background-color: white; -fx-text-fill: black");
        password.setStyle("-fx-background-color: white; -fx-text-fill: black");
        leftPane.setStyle("-fx-background-color: #5bc3f0;");
        rightPane.setStyle("-fx-background-color: #174694;");
        mode.setText("Darkmode");
    }

    protected void darkMode() {
        username.setStyle("-fx-background-color: #000000; -fx-text-fill: white");
        password.setStyle("-fx-background-color: #000000; -fx-text-fill: white");
        rightPane.setStyle("-fx-background-color: #0B234A;");
        leftPane.setStyle("-fx-background-color: #174694;");
        mode.setText("Lightmode");
        mode.setStyle("-fx-text-fill: white;");
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
