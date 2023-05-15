package com.example.aiui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
public class AI {
    @FXML
    private Label antwoord;
    @FXML
    private Label antwoord2;
    @FXML
    private TextField input;

    public void setOnKeyPressed(ActionEvent Enter) {
        antwoord.setText("jammer genoeg hebben wij nog geen antwoord op de vraag: ");
        antwoord2.setText(input.getText());
    }
}
