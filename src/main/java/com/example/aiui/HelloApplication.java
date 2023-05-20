package com.example.aiui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class HelloApplication extends Application {
   data DB = new data();

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("StartLogin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            DB.createDB();
            DB.createTestEmployee();
            stage.setTitle("AIUI: Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}