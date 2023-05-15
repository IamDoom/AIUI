package com.example.aiui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AI {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField chat;
    @FXML
    private Label antwoord;
    @FXML
    private Label antwoord2;


    public void setOnKeyPressed(ActionEvent Enter) {
        antwoord.setText("jammer genoeg hebben wij nog geen antwoord op de vraag: ");
        antwoord2.setText(chat.getText());
    }
}
