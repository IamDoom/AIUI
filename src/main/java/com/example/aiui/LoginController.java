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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    Data db;
    User user;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private Pane Base;
    @FXML private VBox loginpanel;
    @FXML private Label titel;
    @FXML private Button login;
    @FXML private Button Submit;
    @FXML private Button mode;
    @FXML private Button button;
    @FXML private Pane achtergrond;
    @FXML private PasswordField password = new PasswordField();
    @FXML private TextField username = new TextField();
    public LoginController(Data DB) {
        this.db = DB;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemaToepasser();
    }

    @FXML
    protected void submitlogin(ActionEvent event) {
        String password = this.password.getText();
        String username = this.username.getText();
        if (password.isEmpty() || username.isEmpty()) {
            System.out.print(username+"\n"+password);
        } else {
            user = db.login(username, password);
            System.out.print(username+"\n"+password);
            if (user == null) {
            } else {
                try {
                    login(event);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            }
        }
    }

    public void darkmode(){
        achtergrond.setStyle("-fx-background-color: black");
        loginpanel.setStyle("-fx-background-color: black; -fx-border-radius: 24px");
        titel.setStyle("-fx-text-fill: white");
        button.setStyle("-fx-background-color: black");
    }
    public void lightmode(){
        achtergrond.setStyle("-fx-background-color:  linear-gradient(to right, #5bc3f0, #174694)");
        loginpanel.setStyle("-fx-background-color: white; -fx-border-radius: 24px");
        titel.setStyle("-fx-text-fill: linear-gradient(to right, #5bc3f0, #174694)");
        button.setStyle("-fx-background-color:  #5BC3F0");
    }
    public void colormode1(){
        achtergrond.setStyle("-fx-background-color: linear-gradient(to right, darkgreen, lime)");
        loginpanel.setStyle("-fx-background-color: white; -fx-border-radius: 24px");
        titel.setStyle("-fx-text-fill: linear-gradient(to right, darkgreen, lime)");
        button.setStyle("-fx-background-color: darkgreen");
    }
    public void colormode2(){
        achtergrond.setStyle("-fx-background-color: linear-gradient(to right, darkred, red)");
        loginpanel.setStyle("-fx-background-color: white; -fx-border-radius: 24px");
        titel.setStyle("-fx-text-fill: linear-gradient(to right, darkred, red)");
        button.setStyle("-fx-background-color: darkred");
    }
    public void ThemaToepasser() {
        if (ThemaBeheerder.isDarkMode()) {
            darkmode();
        } else if (ThemaBeheerder.isLightMode()) {
            lightmode();
        } else if (ThemaBeheerder.isColorMode1()) {
            colormode1();
        } else if (ThemaBeheerder.isColorMode2()) {
            colormode2();
        }
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScene.fxml"));
        loader.setControllerFactory(type -> new mainController(db, user));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(user.getFirstName()+" "+user.getLastName());
        stage.show();
    }
}