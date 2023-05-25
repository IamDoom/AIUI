package com.example.aiui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

    public class SettingsController implements Initializable {

        @FXML
        private ChoiceBox<String> Choicebox1;
        @FXML
        private ChoiceBox<String> Choicebox2;
        String labelText;
        String ModeText;
        @FXML
        private Label label;

        @FXML
        private Label label2;

        private String[] modes = {"Dark", "Light"};
        private String[] talen = {"Nederlands", "English"};
        private ResourceBundle bundle;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            Choicebox1.getItems().addAll(modes);
            Choicebox2.getItems().addAll(talen);
            bundle = ResourceBundle.getBundle("com.example.aiui.English");
            labelText = bundle.getString("Taal");
            label.setText(labelText);
            ModeText = bundle.getString("Mode");
            label2.setText(ModeText);


            Choicebox2.setValue("English");
            Choicebox2.setOnAction(event -> {
                String lang = Choicebox2.getValue();
                bundle = ResourceBundle.getBundle("com.example.aiui." + lang);
                labelText = bundle.getString("Taal");
                label.setText(labelText);
                ModeText = bundle.getString("Mode");
                label2.setText(ModeText);
            });


        }





        public String getTaal(){
            return Choicebox2.getValue();
        }

        public String getMode(){
            return Choicebox1.getValue();
        }
    }


