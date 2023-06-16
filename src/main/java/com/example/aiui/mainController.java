package com.example.aiui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    User user;
    Data DB;
    Gesprek DitGesprek;
    GespreksManager Manager;
    ArrayList<String> Onderwerpen;
    boolean settings = false;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML  private Button NieuweGesprek;
    private boolean firstMessage = true;
    private int currentGesprekId = 0;
    @FXML private ListView<String> chatList;
    private ResourceBundle bundle = ResourceBundle.getBundle("com.example.aiui.English");
    private boolean EnglishIsActive = true;
    @FXML private ListView<String> GesprekOnderwerpen;
    @FXML private Pane Base;
    @FXML private Button darkmode;
    @FXML private Button mode;
    @FXML private Pane achtergrond;
    @FXML private Label OnderwerpLabel;
    @FXML private Button showSettings;
    @FXML private Button Submit;
    @FXML private Button closeSettings;
    @FXML private Pane settingspane;
    @FXML private Button setting_register;
    @FXML private TextField input;
    @FXML private Pane sidebar;
    @FXML private Button language;
    @FXML private Button lightmode;
    @FXML private Button colormode1;
    @FXML private Button colormode2;
    @FXML private Button logUit;
    @FXML private Label Titel;
    @FXML private Label HisLabel;

    public mainController(Data DB, User user) {
        this.DB = DB;
        this.user = user;
        Manager = user.getGespreksManager();
    }

    @FXML
    public void Togglelang(ActionEvent event) { // voor taal switchen
        if (EnglishIsActive) {
            EnglishIsActive = false;
            bundle = ResourceBundle.getBundle("com.example.aiui.Nederlands");
            setLanguage(bundle);
        } else {
            EnglishIsActive = true;
            bundle = ResourceBundle.getBundle("com.example.aiui.English");
            setLanguage(bundle);
        }
    }
    public void setLanguage(ResourceBundle bundle){
        showSettings.setText(bundle.getString("Settings"));
        input.setPromptText(bundle.getString("PromptText"));
        Submit.setText(bundle.getString("Submit"));
        closeSettings.setText(bundle.getString("closesettings"));
        language.setText(bundle.getString("Taal"));
        setting_register.setText(bundle.getString("settingsregister"));
        HisLabel.setText(bundle.getString("History"));
        logUit.setText(bundle.getString("loguit"));
        darkmode.setText(bundle.getString("DarkMode"));
        lightmode.setText(bundle.getString("LightMode"));
        colormode1.setText(bundle.getString("Thema1"));
        colormode2.setText(bundle.getString("Thema2"));
        NieuweGesprek.setText(bundle.getString("nieuwGesprek"));
    }

    protected void LightMode() {
        achtergrond.setStyle("-fx-background-color:  linear-gradient(to right, #5bc3f0, #174694)");
        GesprekOnderwerpen.setStyle("-fx-background-color: white; -fx-background-radius: 24px; -fx-text-fill: black;");
        chatList.setStyle("-fx-background-color: white; -fx-background-radius: 24px; -fx-text-fill: black;");
        settingspane.setStyle("-fx-background-color: white; -fx-background-radius: 24px;");
        input.setStyle("-fx-background-color: white; -fx-text-fill: black");
        Submit.setStyle("-fx-background-color:  #5BC3F0");
        showSettings.setStyle("-fx-background-color:  #5BC3F0");
        logUit.setStyle("-fx-background-color:  #5BC3F0");
        NieuweGesprek.setStyle("-fx-background-color:  #5BC3F0");
        setting_register.setStyle("-fx-background-color:  #5BC3F0");
        language.setStyle("-fx-background-color:  #5BC3F0");
        darkmode.setStyle("-fx-background-color:  #5BC3F0");
        lightmode.setStyle("-fx-background-color:  #5BC3F0");
        colormode1.setStyle("-fx-background-color:  #5BC3F0");
        colormode2.setStyle("-fx-background-color:  #5BC3F0");
        closeSettings.setStyle("-fx-background-color: #5BC3F0");
    }

    protected void DarkMode() {
        achtergrond.setStyle("-fx-background-color: black");
        GesprekOnderwerpen.setStyle("-fx-background-color: #8a8a8a; -fx-background-radius: 24px; -fx-text-fill: white");
        chatList.setStyle("-fx-background-color: #8a8a8a; -fx-background-radius: 24px; -fx-text-fill: white");
        settingspane.setStyle("-fx-background-color: grey; -fx-background-radius: 24px;");
        input.setStyle("-fx-background-color: darkgrey; -fx-text-fill: white");
        Submit.setStyle("-fx-background-color:   darkgrey");
        showSettings.setStyle("-fx-background-color:   darkgrey");
        logUit.setStyle("-fx-background-color:   darkgrey");
        NieuweGesprek.setStyle("-fx-background-color:   darkgrey");
        setting_register.setStyle("-fx-background-color:   darkgrey");
        language.setStyle("-fx-background-color:   darkgrey");
        darkmode.setStyle("-fx-background-color:   darkgrey");
        lightmode.setStyle("-fx-background-color:   darkgrey");
        colormode1.setStyle("-fx-background-color:   darkgrey");
        colormode2.setStyle("-fx-background-color:   darkgrey");
        closeSettings.setStyle("-fx-background-color:  darkgrey");
    }

    protected void color1() {
        achtergrond.setStyle("-fx-background-color: linear-gradient(to right, darkgreen, lime)");
        settingspane.setStyle("-fx-background-color: darkgreen; -fx-background-radius: 24px;");
        input.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white");
        GesprekOnderwerpen.setStyle("-fx-background-color: white; -fx-background-radius: 24px; -fx-text-fill: black");
        chatList.setStyle("-fx-background-color: white; -fx-background-radius: 24px; -fx-text-fill: black");
        Submit.setStyle("-fx-background-color: green");
        showSettings.setStyle("-fx-background-color: green");
        logUit.setStyle("-fx-background-color: green");
        NieuweGesprek.setStyle("-fx-background-color: green");
        setting_register.setStyle("-fx-background-color: green");
        language.setStyle("-fx-background-color: green");
        darkmode.setStyle("-fx-background-color: green");
        lightmode.setStyle("-fx-background-color: green");
        colormode1.setStyle("-fx-background-color: green");
        colormode2.setStyle("-fx-background-color: green");
        closeSettings.setStyle("-fx-background-color: green");
    }


    protected void color2() {
        achtergrond.setStyle("-fx-background-color: linear-gradient(to right, darkred, red)");
        settingspane.setStyle("-fx-background-color: darkred; -fx-background-radius: 24px;");
        GesprekOnderwerpen.setStyle("-fx-background-color: white; -fx-background-radius: 24px; -fx-text-fill: black");
        chatList.setStyle("-fx-background-color: white; -fx-background-radius: 24px; -fx-text-fill: black");
        input.setStyle("-fx-background-color: darkred; -fx-text-fill: white");
        Submit.setStyle("-fx-background-color: red");
        showSettings.setStyle("-fx-background-color: red");
        logUit.setStyle("-fx-background-color: red");
        NieuweGesprek.setStyle("-fx-background-color: red");
        setting_register.setStyle("-fx-background-color: red");
        language.setStyle("-fx-background-color: red");
        darkmode.setStyle("-fx-background-color: red");
        lightmode.setStyle("-fx-background-color: red");
        colormode1.setStyle("-fx-background-color: red");
        colormode2.setStyle("-fx-background-color: red");
        closeSettings.setStyle("-fx-background-color: red");
    }

    public void ThemaToepasser() {
        if (ThemaBeheerder.isDarkMode()) {
            DarkMode();
        } else if (ThemaBeheerder.isLightMode()) {
            LightMode();
        } else if (ThemaBeheerder.isColorMode1()) {
            color1();
        } else if (ThemaBeheerder.isColorMode2()) {
            color2();
        }
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
        ThemaBeheerder.setDarkMode(darkmode);
        ThemaBeheerder.setLightMode(lightmode);
        ThemaBeheerder.setColorMode1(colormode1);
        ThemaBeheerder.setColorMode2(colormode2);

        // Apply color changes based on the selected theme
        if (darkmode) {
            DarkMode();
        } else if (lightmode) {
            LightMode();
        } else if (colormode1) {
            color1();
        } else if (colormode2) {
            color2();
        }
    }

    @FXML
    protected void displaySettings() {
        settingspane.setVisible(!settings);
        showSettings.setVisible(settings);
        settings = !settings;
    }

    @FXML
    protected void registerEmployee(ActionEvent event) throws IOException {
        Stage stage = (Stage) setting_register.getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            loader.setControllerFactory(type -> new registrationController(DB, EnglishIsActive));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(stage);
            popupStage.setTitle("Register employee");
            popupStage.setScene(new Scene(root));
            // Show the popup window
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ThemaToepasser();
        setLanguage(bundle);
        setting_register.setDisable(!(user instanceof Administrator));

        Onderwerpen = Manager.getOnderwerpen();
        Laadchat(Manager.getGesprek(0).getGesprekDataManager().getGespreksData());
        OnderwerpLabel.setText(Manager.getGesprek(0).getOnderwerp());
        GesprekOnderwerpen.getItems().addAll(Onderwerpen);
    }

    //hier beginnen de methodes voor de chatgeshiedenis
    @FXML
    public void HandleButtonPress(ActionEvent E){
        if(!input.getText().isEmpty()){
            this.HandleMessage();
        }
    }
    public void SetOnKeyPressed(ActionEvent event) {
        if (!input.getText().isEmpty()) {
            this.HandleMessage();
        }
    }

    @FXML
    public void HandleMessage() {
        String userMessage = input.getText();
        //Update het onderwerp als usermessage het eerste bericht it
        if(Manager.getGesprek(currentGesprekId).getGesprekDataManager().getGespreksData().isEmpty()) {
            updateOnderwerp(userMessage);
            firstMessage = false;
        }
        //genereer een response en sla hem op(gebeurt in generateResponseJuisteGesprek)
        String Response = Manager.generateResponseVoorGesprek(Manager.getGesprek(currentGesprekId), userMessage);
        //voeg het berricht toe en clear het textfield
        chatList.getItems().addAll(userMessage);
        chatList.getItems().addAll(Response);
        input.clear();
    }

    public void updateOnderwerp(String onderwerp) {//methode voor updaten van het onderwerp van een chat en het veranderen van het onderwerp on ander plekken waar het wordt gebruikt
        Manager.getGesprek(currentGesprekId).setOnderwerp(onderwerp);
        OnderwerpLabel.setText(onderwerp);
        Onderwerpen.set(currentGesprekId, onderwerp);
        GesprekOnderwerpen.getItems().clear();
        GesprekOnderwerpen.getItems().addAll(Onderwerpen);
    }

    public void NieuwGesprek() {
        //maak gesprek aan en vernieuw de chatgegevens
        Gesprek gesprek = Manager.newGesprek();
        GesprekOnderwerpen.getItems().add(gesprek.getOnderwerp());
        OnderwerpLabel.setText(gesprek.getOnderwerp());
        Onderwerpen.add(gesprek.getOnderwerp());
        currentGesprekId = gesprek.getId();
        chatList.getItems().clear();
    }

    public void SelecteerdChat() {//methode voor het klikken op textsfield, hij checkt welk gesprek je wil zien en laat de inhoud zien **Deze methode werkt alleen als de onderwerpen niet hetzelfde zijn
        chatList.getItems().clear();
        String SelectedChat = GesprekOnderwerpen.getSelectionModel().getSelectedItem();
        if (SelectedChat != null)
            for (Gesprek gesprek : Manager.getGesprekkenLijst()) {
                if (SelectedChat.equals(gesprek.getOnderwerp())) {
                    currentGesprekId = gesprek.getId();
                    Laadchat(gesprek.getGesprekDataManager().getGespreksData());
                }
            }
    }



    public void Laadchat(ArrayList<String> gespreksData) {//methode voor het laden van chats
        chatList.getItems().clear();
        for (int i = 0; i < gespreksData.size(); i++) {
            String str = gespreksData.get(i);
            if (i % 2 == 0) {
                chatList.getItems().add(user.getFirstName() + ": " + str);
            } else {
                chatList.getItems().add("Ai: " + str);
            }
        }
        OnderwerpLabel.setText(Manager.getGesprek(currentGesprekId).getOnderwerp());
    }


    public void UserInfoUpdate(User UpdateUser) {//voor het opslaan van de chatData
        DB.getUserDB().getUsers().set(UpdateUser.getEmployeeID(), UpdateUser);
    }

    @FXML
    public void loguit(ActionEvent event) throws IOException {
        this.UserInfoUpdate(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startLogin.fxml"));
        loader.setControllerFactory(type -> new LoginController(DB));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("AIUI: Login");
        stage.show();
    }
}