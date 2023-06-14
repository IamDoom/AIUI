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
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class loginController implements Initializable {
    public loginController(data DB){
        this.DB = DB;
    }
    data DB;
    User user;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Pane Base;
    @FXML
    private Button login;
    @FXML
    private Button Submit;
    @FXML
    private Button mode;
    @FXML
    private Button button;
    @FXML
    private Pane leftPane;
    @FXML
    private Pane rightPane;
    @FXML
    private PasswordField Password = new PasswordField();
    @FXML
    private TextField Username = new TextField();
    @FXML
    private Label errorMessage = new Label();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
            user = DB.login(username,password);
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

    @FXML
    public void login(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScene.fxml"));
        loader.setControllerFactory(type -> new mainController(DB, user));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(user.getFirstName()+" "+user.getLastName());
        stage.show();
    }
}
