package com.example.aiui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Arrays;

public class registrationController {
    private Stage stage;
    data DB;
    registrationController(data DB){
        this.DB = DB;
    }
    @FXML private Pane Base;
    @FXML private Button cancel;
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

    @FXML void Toggle(ActionEvent event) {

    }
    public void setStage(){
        this.stage = stage;
    }

    private void closeStage(){
        if(stage != null) {
            stage.close();
        }
    }

    protected boolean isFilledin(){//checked of alle velden zijn ingevuld
        for (String s : Arrays.asList(register_firstname.getText(), register_lastname.getText(), register_emailadress.getText(), register_username.getText(), register_password.getText())) {
            if (s.isEmpty()){return false;}
        }
        return true;
    }

    @FXML
    protected void submitRegistration(){
        if (!isFilledin()){
            errorMessage.setText("voer aub alles in!");
        }else{
            String firstname = register_firstname.getText();
            String lastname = register_lastname.getText();
            String email = register_emailadress.getText();
            String username = register_username.getText();
            String password = register_password.getText();
            boolean admin = register_admin.isSelected();
            DB.registerUser(firstname,lastname,email,username,password,admin);
            closeStage();
        }
    }
    @FXML
    protected void Cancel(){
        System.out.println("canceling registration");
        closeStage();
    }
}
