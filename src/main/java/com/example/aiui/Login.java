package com.example.aiui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private TextField Username;
    @FXML
    private TextField Password;
    @FXML
    private Button login;
    ArrayList<LoginGegevens> loginGegevensData;

    public void Login(ActionEvent event) throws IOException {
        String LoginUsername = Username.getText();
        String LoginPassword = Password.getText();
        LoginHandler(LoginUsername, LoginPassword);
    }

    public void LoginHandler(String username, String password){
        if(LoginCheck(username, password)){
            //code for screen transition
        }
        else {
            showAlert("Error", "Onjuiste gegevens");
        }
    }


    private boolean LoginCheck(String a, String b) {
        for (LoginGegevens loginGegevens : loginGegevensData) {
            if (a.equals(loginGegevens.getUsernameKey()) && b.equals(loginGegevens.getPasswordKey())) {
                return true;
            }
        }
        return false;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loginGegevensData = new ArrayList<LoginGegevens>();
        LoginGegevens basicLogin = new LoginGegevens("Admin", "Admin");
        this.loginGegevensData.add(basicLogin);
    }
}
