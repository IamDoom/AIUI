package com.example.aiui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.stage.Modality;

public class mainController {
    Data DB = new Data();
    private Stage stage;
    private Scene scene;
    private Parent root;

    boolean Lightmode = true;

    @FXML
    private ListView<String> chatList;

    @FXML
    private Pane Base;

    @FXML
    private Button mode;

    @FXML
    private Button showSettings;

    @FXML
    private Button closeSettings;

    @FXML
    private Pane settingspane;
    boolean settings = false;

    @FXML
    private Button setting_register;

    @FXML
    private TextField input;

    @FXML
    private Pane sidebar;

    private ObservableList<String> conversation; // Houdt de gesprekken bij

    @FXML
    protected void initialize() {
        conversation = FXCollections.observableArrayList();
        chatList.setItems(conversation);

        // Laad de gesprekken
        loadConversation();
    }

    @FXML
    protected void Toggle(){
        if (Lightmode){
            Lightmode = false;
            Darkmode();
        } else {
            Lightmode = true;
            LightMode();
        }
    }

    protected void LightMode() {
        Base.setStyle("-fx-background-color: #bcc1c4;");
        sidebar.setStyle("-fx-background-color: #307eb3;");
        mode.setStyle("-fx-background-radius: 10; -fx-background-color: white; -fx-border-width: 0;");
        mode.setText("Darkmode");
    }

    protected void Darkmode() {
        Base.setStyle("-fx-background-color: #000000");
        sidebar.setStyle("-fx-background-color: #bcc1c4;");
        mode.setStyle("-fx-background-radius: 10; -fx-background-color: #b6b7ba; -fx-border-width: 0;");
        mode.setText("Lightmode");
    }

    @FXML
    protected void displaySettings(){
        settingspane.setVisible(!settings);
        showSettings.setVisible(settings);
        settings = !settings;
    }

    @FXML
    public void setOnKeyPressed(ActionEvent Enter) {
        String userMessage = input.getText();

        // Voeg het bericht van de gebruiker toe aan de chatgesprekken
        conversation.add("User: " + userMessage);

        // Genereer een automatisch antwoord
        String automaticResponse = generateResponse(userMessage);

        // Voeg het automatische antwoord toe aan de chatgesprekken
        conversation.add("AI: " + automaticResponse);

        // Wis het invoerveld
        input.clear();
    }

    private String generateResponse(String userMessage) {
        // Vervang deze logica door je eigen implementatie of gebruik een AI/chatbot API
        if (userMessage.equalsIgnoreCase("hello")) {
            return "Hi! How can I assist you?";
        } else if (userMessage.equalsIgnoreCase("How are you?") || userMessage.toLowerCase().startsWith("how are you")) {
            return "As an AI, I don't have feelings or emotions, but I'm functioning as intended and ready to help you. How can I assist you today?";
        } else {
            return "I don't have an answer for that now";
        }
    }

    @FXML
    protected void registerEmployee(ActionEvent event) throws IOException{
        Stage stage = (Stage) setting_register.getScene().getWindow();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
            Parent root = fxmlLoader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(stage);
            popupStage.setTitle("Register employee");
            popupStage.setScene(new Scene(root));

            // Toegang tot de controller van het geladen FXML-bestand indien nodig
            registrationController registrationController = fxmlLoader.getController();
            registrationController.setStage();
            // Toon het popup-venster
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void LogoutButton(ActionEvent event) throws IOException {
        // Stap 1: Het gesprek opslaan
        saveConversation();

        // Stap 2: Uitloggen en navigeren naar het startscherm
        root = FXMLLoader.load(getClass().getResource("startLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void saveConversation() {
        // Stap 1: Implementeer hier je logica om het gesprek op te slaan
        // In dit voorbeeld wordt het gesprek opgeslagen naar een tekstbestand met de naam "conversation.txt"
        try {
            FileWriter writer = new FileWriter("conversation.txt");
            for (String message : conversation) {
                writer.write(message + "\n");
            }
            writer.close();
            System.out.println("Gesprek opgeslagen.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadConversation() {
        try {
            // Lees het tekstbestand "conversation.txt"
            BufferedReader reader = new BufferedReader(new FileReader("conversation.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                // Voeg elk bericht toe aan de gesprekkenlijst
                conversation.add(line);
            }

            reader.close();
            System.out.println("Gesprek geladen.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
