package com.example.aiui;

import javafx.application.Application;
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
import java.net.URL;


public class loginController {
    Data DB = new Data();

    private Stage stage;

    private Parent root;
    private Scene scene;
    boolean Lightmode = true;

    @FXML
    private Pane Base;
    @FXML
    private Button login;
    @FXML
    private Button mode;
    @FXML
    private TextField input;
    @FXML
    private Pane sidebar;
    @FXML
    private Button submit;
    @FXML
    private PasswordField Password = new PasswordField();
    @FXML
    private TextField Username = new TextField();
    @FXML
    private Label errorMessage = new Label();

    /**
     * Methode om tussen lichte en donkere modus te schakelen
     */
    @FXML
    protected void Toggle() {
        URL fxml = getClass().getResource("fxml/startLogin");

        if (mode.getText().equals("Darkmode")) {

            String css1 = this.getClass().getResource("css/LoginLight.css").toExternalForm();
            scene.getStylesheets().add(css1);
            mode.setText("Lightmode");

        } else {
            String css = this.getClass().getResource("css/LoginDark.css").toExternalForm();
            scene.getStylesheets().add(css);
            mode.setText("Darkmode");


        }
    }

    /**
     * Methode om in te loggen na het indrukken van de login knop
     * @param event Het actie-evenement
     */
    @FXML
    protected void submitLogin(ActionEvent event) {
        String password = Password.getText();
        String username = Username.getText();

        if (password.isEmpty() || username.isEmpty()) {
            errorMessage.setText("Wachtwoord of gebruikersnaam incompleet");
            System.out.println("Wachtwoord of gebruikersnaam incompleet");
            System.out.println(username);
            System.out.println(password);
        } else {
            User user = DB.login(username, password);
            System.out.println(username);
            System.out.println(password);

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
        Base.setStyle("-fx-background-color: #bcc1c4;");
        sidebar.setStyle("-fx-background-color: #307eb3;");
        mode.setStyle("-fx-background-radius: 10; -fx-background-color: white; -fx-border-width: 0;");
        mode.setText("Darkmode");
    }

    /**
     * Methode om de donkere modus in te schakelen
     */
    protected void darkMode() {
        Base.setStyle("-fx-background-color: #000000");
        sidebar.setStyle("-fx-background-color: #bcc1c4;");
        mode.setStyle("-fx-background-radius: 10; -fx-background-color: #b6b7ba; -fx-border-width: 0;");
        mode.setText("Lightmode");
    }

    /**
     * Methode om in te loggen en de hoofdscene te laden
     * @param event Het actie-evenement
     * @throws IOException Als er een fout optreedt bij het laden van de fxml-bestanden
     */
    @FXML
    public void login(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
