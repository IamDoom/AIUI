package com.example.aiui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AI {
    boolean Lightmode = true;

    @FXML
    private ListView<String> chatList;
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
    public void setOnKeyPressed(ActionEvent Enter) {
        String userMessage = input.getText();

        // Voeg het bericht van de gebruiker toe aan de chatlijst
        chatList.getItems().add("Gebruiker: " + userMessage);

        // Genereer een automatisch antwoord
        String automaticResponse = generateResponse(userMessage);

        // Voeg het automatische antwoord toe aan de chatlijst
        chatList.getItems().add("AI: " + automaticResponse);

        // Maak het invoerveld leeg
        input.clear();
    }

    protected void LightMode() {
        Base.setStyle("-fx-background-color: #bcc1c4;");
        sidebar.setStyle("-fx-background-color: #307eb3;");
        mode.setStyle("-fx-background-radius: 10; -fx-background-color: white; -fx-border-width: 0;");
        submit.setStyle("-fx-background-color: #307eb3");
        mode.setText("Donkere modus");
        chatList.setStyle("-fx-control-inner-background: #307eb3; -fx-text-fill: black");
    }

    protected void Darkmode() {
        Base.setStyle("-fx-background-color: #000000");
        sidebar.setStyle("-fx-background-color: #bcc1c4;");
        mode.setStyle("-fx-background-radius: 10; -fx-background-color: #b6b7ba; -fx-border-width: 0;");
        submit.setStyle("-fx-background-color: #b6b7ba;");
        mode.setText("Lichte modus");
        chatList.setStyle("-fx-control-inner-background: black; -fx-text-fill: white");
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

    private String generateResponse(String userMessage) {
        // Replace this logic with your own or use an AI/chatbot API
        if (userMessage.equalsIgnoreCase("hello")) {
            return "Hi! How can I assist you?";
        } else if (userMessage.equalsIgnoreCase("How are you?") || userMessage.toLowerCase().startsWith("how are you")) {
            return "As an AI, I don't have feelings or emotions, but I'm functioning as intended and ready to help you. How can I assist you today?";
        } else {
                return "I don't have an answer for that now";
        }
    }
}
