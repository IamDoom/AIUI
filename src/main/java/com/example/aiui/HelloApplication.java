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
        DB.registerUser("john", "doe", "johndoe@emailadress.com","testusername", "securepassword", false);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("startLogin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("AIUI: Login");
            loginController loginController = loader.getController();
            loginController.setDB(DB);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}