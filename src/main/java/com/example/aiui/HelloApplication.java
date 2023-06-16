package com.example.aiui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    Data DB = new Data();
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("startLogin.fxml"));
            loader.setControllerFactory(type -> new LoginController(DB));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("AIUI: Login");
            stage.setResizable(false);
            stage.setScene(scene);
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