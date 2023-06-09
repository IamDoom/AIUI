package com.example.aiui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Modality;

public class mainController {
    data DB = new data();
    private Stage stage;
    private Scene scene;
    private Parent root;

    boolean Lightmode = true;

    private ResourceBundle bundle = ResourceBundle.getBundle("com.example.aiui.English");
    private boolean EnglishIsActive = true;

    @FXML
    private ListView<String> chatList;

    @FXML
    private Pane Base;

    @FXML
    private Button mode;

    @FXML
    private Label OnderwerpLabel;

    @FXML
    private Button showSettings;

    @FXML
    private Button Submit;

    @FXML
    private Button closeSettings;

    @FXML
    private Button newChat;


    @FXML
    private Pane settingspane;
    boolean settings = false;

    @FXML
    private Button setting_register;

    @FXML
    private TextField input;


    @FXML
    private Pane sidebar;

    @FXML
    private Button edituser;

    @FXML
    private Button language;

    @FXML
    private Button advanced;

    private ObservableList<String> conversation;

    private boolean FirstMessage = true;


    public mainController(){

    }

    modesData modesData = new modesData();



    public void Togglelang(ActionEvent event){ // voor taal switchen
        if (EnglishIsActive){
            EnglishIsActive = false;
            bundle = ResourceBundle.getBundle("com.example.aiui.Nederlands");
            showSettings.setText(bundle.getString("Settings"));
            input.setPromptText(bundle.getString("PromptText"));
            Submit.setText(bundle.getString("Submit"));
            closeSettings.setText(bundle.getString("closesettings"));
            advanced.setText(bundle.getString("advanced"));
            edituser.setText(bundle.getString("edituser"));
            language.setText(bundle.getString("Taal"));
            setting_register.setText(bundle.getString("settingsregister"));


        } else {
            EnglishIsActive = true;
            bundle = ResourceBundle.getBundle("com.example.aiui.English");
            showSettings.setText(bundle.getString("Settings"));
            input.setPromptText(bundle.getString("PromptText"));
            Submit.setText(bundle.getString("Submit"));
            closeSettings.setText(bundle.getString("closesettings"));
            advanced.setText(bundle.getString("advanced"));
            edituser.setText(bundle.getString("edituser"));
            language.setText(bundle.getString("Taal"));
            setting_register.setText(bundle.getString("settingsregister"));
        }

    }



    protected void Lightmode() {
        Base.setStyle("-fx-background-color: #bcc1c4;");
        sidebar.setStyle("-fx-background-color: #307eb3;");
        mode.setStyle("-fx-background-radius: 10; -fx-background-color: white; -fx-border-width: 0;");
        mode.setText("Darkmode");
    }

    protected void DarkMode() {
        input.setStyle("-fx-text-fill: white;");
        Base.setStyle("-fx-background-color: #000000");
        sidebar.setStyle("-fx-background-color: #bcc1c4;");
        mode.setStyle("-fx-background-radius: 10; -fx-background-color: #b6b7ba; -fx-border-width: 0;");
        mode.setText("Lightmode");
    }

    protected void color1() {

    }

    protected void color2() {

    }
    @FXML
    public void darkKlick() {
        boolean darkmode = true;
        boolean lightmode = false;
        boolean colormode1 =  false;
        boolean colormode2 = false;
        update(true, false, false, false);
    }
    @FXML
    public void lightKlick() {
        boolean darkmode = false;
        boolean lightmode = true;
        boolean colormode1 =  false;
        boolean colormode2 = false;
        update(false, true, false, false);
    }
    @FXML
    public void color1Klick() {
        boolean darkmode = false;
        boolean lightmode = false;
        boolean colormode1 =  true;
        boolean colormode2 = false;
        update(false, false, true, false);
    }
    @FXML
    public void color2Klick() {
        boolean darkmode = false;
        boolean lightmode = false;
        boolean colormode1 =  false;
        boolean colormode2 = true;
        update(false, false, false, true);
    }

    @Override
    public void update(boolean darkmode, boolean lightmode, boolean colormode1, boolean colormode2) {
        modesData.notifyObservers();
        if (darkmode == true) {
            modesData.setmode(true, false, false, false);
            DarkMode();
        } else if (lightmode == true) {
            modesData.setmode(false, true, false, false);
            Lightmode();
        } else if (colormode1 == true) {
            modesData.setmode(false, false, true, false);
            color1();
        } else if (colormode2 == true) {
            modesData.setmode(false, false, false, true);
            color2();
        }


    }


        /**
         * Methode om de instellingen weer te geven of te verbergen
         */
        @FXML
        protected void displaySettings () {
            settingspane.setVisible(!settings);
            showSettings.setVisible(settings);
            settings = !settings;
        }


    @FXML
    public void setOnKeyPressed(ActionEvent Enter) {
        String userMessage = input.getText();
        @FXML
        public void setOnKeyPressed (ActionEvent Enter){
            String userMessage = input.getText();
            if (FirstMessage) {
                OnderwerpLabel.setText(userMessage);
                FirstMessage = false;
            }

        // Add user's message to the chat list
        chatList.getItems().add("User: " + userMessage);

        // Generate an automatic response
        String automaticResponse = generateResponse(userMessage);

        // Add the automatic response to the chat list
        chatList.getItems().add("AI: " + automaticResponse);

        // Clear the input field
        input.clear();
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

            // Access the controller of the loaded FXML file if needed
            registrationController registrationController = fxmlLoader.getController();
            registrationController.setStage();
            // Show the popup window
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showSettings.setText(bundle.getString("Settings"));
        input.setPromptText(bundle.getString("PromptText"));
        Submit.setText(bundle.getString("Submit"));
        closeSettings.setText(bundle.getString("closesettings"));
        advanced.setText(bundle.getString("advanced"));
        edituser.setText(bundle.getString("edituser"));
        language.setText(bundle.getString("Taal"));
        setting_register.setText(bundle.getString("settingsregister"));
        conversation = FXCollections.observableArrayList();
        chatList.setItems(conversation);

    }
}
