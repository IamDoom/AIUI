package com.example.aiui;

import javafx.event.ActionEvent;
import javafx.event.Event;
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


public class HelloController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    boolean Lightmode = true;
    @FXML
    private Pane Base;

    @FXML
    private Button mode;

    @FXML
    private TextField input;

    @FXML
    private Pane sidebar;

    @FXML
    private Button submit;
    @FXML
    private PasswordField Password;
    @FXML
    private TextField username;
    @FXML
    private Label errorMessage;

    @FXML
    protected void Toggle(){
        if (Lightmode){
            Lightmode = false;
            Darkmode();
        } else {
            Lightmode = true;
            LightMode();
        }

    }
    @FXML
    protected void submitlogin() {
        if (Password.getText().isEmpty() || username.getText().isEmpty()) {
            errorMessage.setText("Wachtwoord of gebruikersnaam incompleet");
        } else {
            try {
                switchToScene2(null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void LightMode() {
        Base.setStyle("-fx-background-color: #bcc1c4;");
        sidebar.setStyle("-fx-background-color: #307eb3;");
        mode.setStyle("-fx-background-radius: 10; -fx-background-color: white; -fx-border-width: 0;");
        mode.setText("Darkmode");
    }
    protected void Darkmode() {
        Base.setStyle("-fx-background-color: #000000");
        sidebar.setStyle("-fx-background-color: #bcc1c4;");
        mode.setStyle("-fx-background-radius: 10; -fx-background-color: #b6b7ba; -fx-border-width: 0;");
        mode.setText("Lightmode");
    }

    @FXML
    public void switchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
