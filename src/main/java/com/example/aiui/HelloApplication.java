package com.example.aiui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.util.Objects;

public class helloApplication extends Application {
    Data DB = new Data();

    @Override
    public void start(Stage stage) {
        try {
            // Het laden van het FXML-bestand
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("startLogin.fxml")));

            // Creëren van een scene
            Scene scene = new Scene(root);

            // Het instellen van de scene op het podium
            stage.setScene(scene);

            // Database en testmedewerker aanmaken
            DB.createDB();
            DB.createTestEmployee();

            // Het instellen van de titel van het venster
            stage.setTitle("AIUI: Login");

            // Het tonen van het venster
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Starten van de JavaFX-toepassing
        launch(args);
    }
}
