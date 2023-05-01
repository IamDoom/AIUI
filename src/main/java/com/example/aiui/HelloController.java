package com.example.aiui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private SplitPane pane;
    @FXML
    private TextField vraag;
    @FXML
    private Label antwoord;

    @FXML
    private void onVraagEnter() {
        String input = vraag.getText();
        antwoord.setText("You asked: " + input);
    }
}
