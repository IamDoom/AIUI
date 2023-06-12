package com.example.aiui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.util.Observer;

public class HelloApplication extends Application {
    data DB = new data();
    @Override
    public void start(Stage stage) {
        DB.registerUser("john", "doe", "johndoe@emailadress.com","testusername", "securepassword", false);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(com.example.aiui.HelloApplication.class.getResource("startLogin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("AIUI: Login");
            loginController loginController = fxmlLoader.getController();
            loginController.setDB(DB);
            stage.setScene(scene);
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
