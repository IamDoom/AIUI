package com.example.aiui;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.skin.ToggleButtonSkin;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.stage.Modality;

public class mainController implements Initializable{
    User user;
    data DB;
    public mainController(data DB, User user){
        this.DB = DB;
        this.user = user;
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    private ResourceBundle bundle = ResourceBundle.getBundle("com.example.aiui.English");
    private boolean EnglishIsActive = true;
    private Button NieuweGesprek;
    private boolean firstMessage = true;
    private int currentGesprekId = 0;
    ArrayList<String> Onderwerpen;
    boolean settings = false;

    @FXML private ListView<String> chatList;
    @FXML private Pane Base;
    @FXML private Button mode;
    @FXML private Label OnderwerpLabel;
    @FXML private Button showSettings;
    @FXML private Button Submit;
    @FXML private Button closeSettings;
    @FXML private Pane settingspane;
    @FXML private Button setting_register;
    @FXML private TextField input;
    @FXML private Pane sidebar;
    @FXML private Button edituser;
    @FXML private Button language;
    @FXML private Button advanced;
    @FXML private ListView<String> GesprekOnderwerpen;

    @FXML
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
    protected void DarkMode() {
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
        update(true, false, false, false);
    }
    @FXML
    public void lightKlick() {
        update(false, true, false, false);
    }
    @FXML
    public void color1Klick() {
        update(false, false, true, false);
    }
    @FXML
    public void color2Klick() {
        update(false, false, false, true);
    }


    public void update(boolean darkmode, boolean lightmode, boolean colormode1, boolean colormode2) {
        if (darkmode == true) {
            DarkMode();
        } else if (lightmode == true) {
            LightMode();
        } else if (colormode1 == true) {
            color1();
        } else if (colormode2 == true) {
            color2();
        }
    }

    @FXML
    protected void displaySettings(){
        settingspane.setVisible(!settings);
        showSettings.setVisible(settings);
        settings = !settings;
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
            registrationController.setregistrationController(DB);
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

        Onderwerpen = user.getGespreksManager().getOnderwerpen();
        Laadchat(user.getGespreksManager().getGesprek(0).getGespreksData());
        OnderwerpLabel.setText(user.getGespreksManager().getGesprek(0).getOnderwerp());
        GesprekOnderwerpen.getItems().addAll(Onderwerpen);
    }

    //hier beginnen de methodes voor de chatgeshiedenis
    @FXML
    public void setOnKeyPressed(ActionEvent Enter) {
        String userMessage = input.getText();
        //Update het onderwerp als usermessage het eerste bericht it
        if(firstMessage){
            updateOnderwerp(userMessage);
            firstMessage=false;
        }
        //genereer een response en sla hem op(gebeurt in generateResponseJuisteGesprek)
        String Response = user.getGespreksManager().GenerateResponseJuisteGesprek(user.getGespreksManager().getGesprek(currentGesprekId), userMessage);
        //voeg het berricht toe en clear het textfield
        chatList.getItems().addAll(userMessage);
        chatList.getItems().addAll(Response);
        input.clear();
    }
    public void updateOnderwerp(String onderwerp){//methode voor updaten van het onderwerp van een chat en het veranderen van het onderwerp on ander plekken waar het wordt gebruikt
        user.getGespreksManager().getGesprek(currentGesprekId).setOnderwerp(onderwerp);
        OnderwerpLabel.setText(onderwerp);
        Onderwerpen.set(currentGesprekId, onderwerp);
        GesprekOnderwerpen.getItems().clear();
        GesprekOnderwerpen.getItems().addAll(Onderwerpen);
    }

    public void NieuwGesprek(){
        //firstmessage belangrijk voor setonclick methode
        firstMessage = true;
        //maak gesprek aan en vernieuw de chatgegevens
        Gesprek gesprek = user.getGespreksManager().newGesprek();
        GesprekOnderwerpen.getItems().add(gesprek.getOnderwerp());
        OnderwerpLabel.setText(gesprek.getOnderwerp());
        Onderwerpen.add(gesprek.getOnderwerp());
        currentGesprekId = gesprek.getId();
        chatList.getItems().clear();
    }

    public void SelecteerdChat(){//methode voor het klikken op textsfield, hij checkt welk gesprek je wil zien en laat de inhoud zien **Deze methode werkt alleen als de onderwerpen niet hetzelfde zijn
        chatList.getItems().clear();
        String SelectedChat = GesprekOnderwerpen.getSelectionModel().getSelectedItem();
        for(Gesprek gesprek : user.getGespreksManager().getGesprekken()) {
            if (SelectedChat.equals(gesprek.getOnderwerp())) {
                currentGesprekId = gesprek.getId();
                Laadchat(gesprek.getGespreksData());
            }
        }
    }
    public void Laadchat(ArrayList<String> gespreksData){//methode voor het laden van chats
        chatList.getItems().clear();
        for (int i = 0; i < gespreksData.size(); i++) {
            String str = gespreksData.get(i);
            if (i % 2 == 0) {
                chatList.getItems().add(user.getFirstName() + ": " + str);
            } else {
                chatList.getItems().add("Ai: " + str);
            }
        }
        OnderwerpLabel.setText(user.getGespreksManager().getGesprek(currentGesprekId).getOnderwerp());
    }



    public void WeizigOnderwerp(){
        //weizig het onderwerp code
        GesprekOnderwerpen.refresh();
    }
    public void UserInfoUpdate(User UpdateUser){//voor het opslaan van de chatData
        DB.getUserDB().getUsers().set(UpdateUser.getEmployeeID(), UpdateUser);
    }

    @FXML
    public void loguit(ActionEvent event) throws IOException {
        this.UserInfoUpdate(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startLogin.fxml"));
        loader.setControllerFactory(type -> new loginController(DB));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(user.getFirstName()+" "+user.getLastName());
        stage.show();
    }
}
