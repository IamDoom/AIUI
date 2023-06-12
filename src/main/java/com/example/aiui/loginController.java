package com.example.aiui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class loginController implements observer, Initializable {
    Data DB;
    public void setDB(Data DB){
        this.DB = DB;
    }

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
    private PasswordField Password;
    @FXML
    private TextField Username;
    @FXML
    private Label errorMessage;
    public loginController(){

    }
    modesData modesData = new modesData();
    @Override
    public void update(boolean darkmode, boolean lightmode, boolean colormode1, boolean colormode2){
        modesData.notifyObservers();
        if (darkmode == true) {
            modesData.setmode(true, false, false, false);
            darkMode();
        } else if (lightmode == true) {
            modesData.setmode(false, true, false, false);
            lightMode();
        } else if (colormode1 == true) {
            modesData.setmode(false, false, true, false);
            color1();
        } else if (colormode2 == true) {
            modesData.setmode(false, false, false, true);
            color2();
        }
    }
    @FXML
    protected void Toggle() {
update(modesData.getdarkmode(), modesData.getlightmode(), modesData.getcolor1(), modesData.getcolor2());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    mode.fire();
    }

    @FXML
    protected void submitlogin(ActionEvent event) {
        String password = Password.getText();
        String username = Username.getText();
        if (password.isEmpty() || username.isEmpty()) {
            errorMessage.setText("Wachtwoord of gebruikersnaam incompleet");
            System.out.println(username);
            System.out.println(password);
        } else {
            User user = DB.login(username,password);
            System.out.println(username);
            System.out.println(password);
            if (user == null){
                errorMessage.setText("Wachtwoord of gebruikersnaam incorrect");
            } else {
                try {
                    System.out.println("login succesfull");
                    login(event);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            }
        }
    }

    protected void lightMode() {
        Username.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        Password.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        leftPane.setStyle("-fx-background-color: #5bc3f0;");
        rightPane.setStyle("-fx-background-color: #174694;");
        mode.setText("Donkere modus");
        mode.setStyle("-fx-background-color: #5BC3F0");
        button.setStyle("-fx-background-color: #5BC3F0");
    }

    protected void darkMode() {
        Username.setStyle("-fx-background-color: #1b8bc5; -fx-text-fill: white;");
        Password.setStyle("-fx-background-color: #1b8bc5; -fx-text-fill: white;");
        leftPane.setStyle("-fx-background-color: #1b8bc5;");
        rightPane.setStyle("-fx-background-color: #174694;");
        mode.setText("Lichte modus");
        mode.setStyle("-fx-background-color: #1b8bc5");
        button.setStyle("-fx-background-color: #1b8bc5");
    }
    protected void color1() {
        Username.setStyle("-fx-background-color: #1b8bc5; -fx-text-fill: white;");
        Password.setStyle("-fx-background-color: #1b8bc5; -fx-text-fill: white;");
        leftPane.setStyle("-fx-background-color: #1b8bc5;");
        rightPane.setStyle("-fx-background-color: #174694;");
        mode.setText("Lichte modus");
        mode.setStyle("-fx-background-color: #1b8bc5");
        button.setStyle("-fx-background-color: #1b8bc5");
    }
    protected void color2() {
        Username.setStyle("-fx-background-color: #1b8bc5; -fx-text-fill: white;");
        Password.setStyle("-fx-background-color: #1b8bc5; -fx-text-fill: white;");
        leftPane.setStyle("-fx-background-color: #1b8bc5;");
        rightPane.setStyle("-fx-background-color: #174694;");
        mode.setText("Lichte modus");
        mode.setStyle("-fx-background-color: #1b8bc5");
        button.setStyle("-fx-background-color: #1b8bc5");
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}


