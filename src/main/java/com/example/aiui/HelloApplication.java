package com.example.aiui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.util.Observer;

public class HelloApplication extends Application {
    Data DB = new Data();
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
    modesData modesData = new modesData();
    observer logincontroller = new loginController();
    observer maincontroller = new mainController();
    modesData.registerObserver(maincontroller);
    modesData.registerObserver(logincontroller);
        // Starten van de JavaFX-toepassing
        launch(args);

    }
}
