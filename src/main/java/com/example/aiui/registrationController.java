package com.example.aiui;

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
    private Stage stage;
    data DB;

    public registrationController(data DB1) {
        this.DB = DB1;
    }

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

    @FXML
    void Toggle(ActionEvent event) {}

    public void setStage() {
        this.stage = stage;
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
    }

    private void closeStage() {
        if (stage != null) {
            stage.close();
        }
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
    protected void submitRegistration() {
        if (!isFilledin()) {
            errorMessage.setText("voer aub alles in!");
        } else {
            String firstname = register_firstname.getText();
            String lastname = register_lastname.getText();
            String email = register_emailadress.getText();
            String username = register_username.getText();
            String password = register_password.getText();
            boolean admin = register_admin.isSelected();
            DB.registerUser(firstname, lastname, email, username, password, admin);
            closeStage();
        }
    }

    @FXML
    protected void Cancel() {
        System.out.println("canceling registration");
        closeStage();
    }
}
