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
import java.util.Objects;
public class AI {
    boolean Lightmode = true;

    @FXML
    private Label antwoord;
    @FXML
    private Label antwoord2;
    @FXML
    private TextField input;
    @FXML
    private Pane Base;
    @FXML
    private Pane sidebar;
    @FXML
    private Button mode;
    @FXML
    private Button submit;


        @FXML
        public void setOnKeyPressed(ActionEvent Enter)  {
            antwoord.setText("jammer genoeg hebben wij nog geen antwoord op de vraag: ");
            antwoord2.setText(input.getText());
        }
        protected void LightMode () {
            Base.setStyle("-fx-background-color: #bcc1c4;");
            sidebar.setStyle("-fx-background-color: #307eb3;");
            mode.setStyle("-fx-background-radius: 10; -fx-background-color: white; -fx-border-width: 0;");
            submit.setStyle("-fx-background-color: #307eb3");
            mode.setText("Darkmode");
        }
        protected void Darkmode () {
            Base.setStyle("-fx-background-color: #000000");
            sidebar.setStyle("-fx-background-color: #bcc1c4;");
            mode.setStyle("-fx-background-radius: 10; -fx-background-color: #b6b7ba; -fx-border-width: 0;");
            submit.setStyle("-fx-background-color: #b6b7ba;");
            mode.setText("Lightmode");
        }
    @FXML
    protected void Toggle() {
        if (Lightmode) {
            Lightmode = false;
            Darkmode();
        } else {
            Lightmode = true;
            LightMode();
        }

    }
}
