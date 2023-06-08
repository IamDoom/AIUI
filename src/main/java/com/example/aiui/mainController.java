package com.example.aiui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.skin.ToggleButtonSkin;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Modality;

public class mainController implements Initializable, Observer {
    Data DB = new Data();
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





    private boolean FirstMessage = true;

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

    @Override
    public void update(boolean darkmode, boolean lightmode, boolean colormode1, boolean colormode2) {
        if (darkmode = true){

        }
    }

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
        if(FirstMessage){
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

    }
}
