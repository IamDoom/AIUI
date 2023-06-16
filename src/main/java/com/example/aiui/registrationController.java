package com.example.aiui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class registrationController implements Initializable {
    Data DB;

    public registrationController(Data DB, boolean englishIsActive) {
        this.DB = DB;
        this.EnglishLanguage = englishIsActive;
    }
    boolean EnglishLanguage;
    ResourceBundle bundle;

    @FXML private Pane achtergrond;
    @FXML private Button cancel;
    @FXML private Button submit;
    @FXML private Label titel;
    @FXML private VBox registerpanel;
    @FXML private Label errorMessage;
    @FXML private Button login;
    @FXML private Button mode;
    @FXML private CheckBox register_admin = new CheckBox();
    @FXML private TextField register_emailadress = new TextField();
    @FXML private TextField register_firstname = new TextField();
    @FXML private TextField register_lastname = new TextField();
    @FXML private PasswordField register_password = new PasswordField();
    @FXML private TextField register_username = new TextField();
    @FXML private Pane sidebar;
    @FXML private Label Firstname;
    @FXML private Label Lastname;
    @FXML private Label Email;
    @FXML private Label Username;
    @FXML private Label Password;
    public void handler(){
        if (EnglishLanguage){
            bundle = ResourceBundle.getBundle("com.example.aiui.English");
            setLanguage();
        }
        else{
            bundle = ResourceBundle.getBundle("com.example.aiui.Nederlands");
            setLanguage();
        }

    }

    public void setLanguage(){
        Firstname.setText(bundle.getString("Voornaam"));
        Lastname.setText(bundle.getString("Achternaam"));
        Email.setText(bundle.getString("email"));
        Username.setText(bundle.getString("username"));
        Password.setText(bundle.getString("password"));
        register_firstname.setPromptText(bundle.getString("voornaam1"));
        register_lastname.setPromptText(bundle.getString("achternaam1"));
        register_emailadress.setPromptText(bundle.getString("email2"));
        register_username.setPromptText(bundle.getString("username"));
        register_password.setPromptText(bundle.getString("password"));
        submit.setText(bundle.getString("Submit2"));
        cancel.setText(bundle.getString("Cancel"));
        titel.setText(bundle.getString("Registration"));
    }

    public void ThemaToepasser() {
        if (ThemaBeheerder.isDarkMode()) {
            achtergrond.setStyle("-fx-background-color: black");
            registerpanel.setStyle("-fx-background-color: black; -fx-border-radius: 24px");
            titel.setStyle("-fx-text-fill: lightgray");
            submit.setStyle("-fx-background-color: black");
            cancel.setStyle("-fx-background-color: black");
        } else if (ThemaBeheerder.isLightMode()) {
            achtergrond.setStyle("-fx-background-color:  linear-gradient(to right, #5bc3f0, #174694)");
            registerpanel.setStyle("-fx-background-color: white; -fx-border-radius: 24px");
            titel.setStyle("-fx-text-fill: white");
            submit.setStyle("-fx-background-color:  #5BC3F0");
            cancel.setStyle("-fx-background-color:  #5BC3F0");
        } else if (ThemaBeheerder.isColorMode1()) {
            achtergrond.setStyle("-fx-background-color: linear-gradient(to right, darkgreen, lime)");
            registerpanel.setStyle("-fx-background-color: white; -fx-border-radius: 24px");
            titel.setStyle("-fx-text-fill: white");
            submit.setStyle("-fx-background-color: darkgreen");
            cancel.setStyle("-fx-background-color: darkgreen");
        } else if (ThemaBeheerder.isColorMode2()) {
            achtergrond.setStyle("-fx-background-color: linear-gradient(to right, darkred, red)");
            registerpanel.setStyle("-fx-background-color: white; -fx-border-radius: 24px");
            titel.setStyle("-fx-text-fill: white");
            submit.setStyle("-fx-background-color: darkred");
            cancel.setStyle("-fx-background-color: darkred");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemaToepasser();
        handler();
    }

    public void closePopup() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    protected boolean isFilledin() {
        String firstname = register_firstname.getText();
        String lastname = register_lastname.getText();
        String email = register_emailadress.getText();
        String username = register_username.getText();
        String password = register_password.getText();
        if (firstname.isEmpty()) {
            return false;
        }
        if (lastname.isEmpty()) {
            return false;
        }
        if (email.isEmpty()) {
            return false;
        }
        if (username.isEmpty()) {
            return false;
        }
        if (password.isEmpty()) {
            return false;
        }
        return true;
    }

    @FXML
    protected void submitRegistration(ActionEvent event) throws IOException {
        if (!isFilledin()) {
            errorMessage.setText("Please fill in all fields!");
        } else {
            String firstname = register_firstname.getText();
            String lastname = register_lastname.getText();
            String email = register_emailadress.getText();
            String username = register_username.getText();
            String password = register_password.getText();

            DB.setTempfirstName(firstname);
            DB.setTemplastName(lastname);
            DB.setTempemail(email);
            DB.setTempusername(username);
            DB.settempPassword(password);
            boolean admin = register_admin.isSelected();
            if(DB.registerUser(admin)) {

                // Clear the text fields
                register_firstname.clear();
                register_lastname.clear();
                register_emailadress.clear();
                register_username.clear();
                register_password.clear();

                // Show the registration success message
                String message = "User " + firstname + " " + lastname + " registered with username " + username;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration Success");
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
            }
            else {
                String message = "PasswordStrength is too low";
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration failed");
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
            }
        }
    }


    @FXML
    protected void Cancel() {
        System.out.println("canceling registration");
        closePopup();
    }
}
