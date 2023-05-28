package com.example.aiui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class mainController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    boolean lightMode = true;

    @FXML
    private ListView<String> chatList;

    @FXML
    private Pane base;

    @FXML
    private Button mode;

    @FXML
    private Button showSettings;

    @FXML
    private Button closeSettings;

    @FXML
    private Pane settingsPane;
    boolean settings = false;

    @FXML
    private TextField input;

    @FXML
    private Pane sidebar;

    /**
     * Methode om tussen lichte en donkere modus te schakelen
     */
    @FXML
    protected void toggle() {
        if (lightMode) {
            lightMode = false;
            darkMode();
        } else {
            lightMode = true;
            lightMode();
        }
    }

    /**
     * Methode om de lichte modus in te schakelen
     */
    protected void lightMode() {
        base.setStyle("-fx-background-color: #bcc1c4;");
        sidebar.setStyle("-fx-background-color: #307eb3;");
        mode.setStyle("-fx-background-radius: 10; -fx-background-color: white; -fx-border-width: 0;");
        mode.setText("Darkmode");
    }

    /**
     * Methode om de donkere modus in te schakelen
     */
    protected void darkMode() {
        base.setStyle("-fx-background-color: #000000");
        sidebar.setStyle("-fx-background-color: #bcc1c4;");
        mode.setStyle("-fx-background-radius: 10; -fx-background-color: #b6b7ba; -fx-border-width: 0;");
        mode.setText("Lightmode");
    }

    /**
     * Methode om de instellingen weer te geven of te verbergen
     */
    @FXML
    protected void displaySettings() {
        settingsPane.setVisible(!settings);
        showSettings.setVisible(settings);
        settings = !settings;
    }

    /**
     * Methode om te reageren op de gebruikersinvoer en een automatisch antwoord te genereren
     * @param enter Het actie-evenement
     */
    @FXML
    public void setOnKeyPressed(ActionEvent enter) {
        String userMessage = input.getText();

        // Voeg het bericht van de gebruiker toe aan de chatlijst
        chatList.getItems().add("User: " + userMessage);

        // Genereer een automatisch antwoord
        String automaticResponse = generateResponse(userMessage);

        // Voeg het automatische antwoord toe aan de chatlijst
        chatList.getItems().add("AI: " + automaticResponse);

        // Maak het invoerveld leeg
        input.clear();
    }

    /**
     * Methode om een automatisch antwoord te genereren op basis van de gebruikersinvoer
     * @param userMessage De invoer van de gebruiker
     * @return Het automatische antwoord
     */
    private String generateResponse(String userMessage) {
        // Vervang deze logica door je eigen logica of gebruik een AI/chatbot API
        if (userMessage.equalsIgnoreCase("hello")) {
            return "Hi! How can I assist you?";
        } else if (userMessage.equalsIgnoreCase("How are you?") || userMessage.toLowerCase().startsWith("how are you")) {
            return "As an AI, I don't have feelings or emotions, but I'm functioning as intended and ready to help you. How can I assist you today?";
        } else {
            return "I don't have an answer for that now";
        }
    }
}
