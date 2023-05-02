package com.example.aiui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class HelloApplication extends Application {

    private Scene scene1;
    private Scene scene2;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        // Load Screen1.fxml
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("HelloController.fxml"));
        Parent root1 = loader1.load();
        HelloController screen1Controller = loader1.getController();
        scene1 = new Scene(root1);

        // Load Screen2.fxml
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("AIcontroller.fxml"));
        Parent root2 = loader2.load();
        AIcontroller screen2Controller = loader2.getController();
        scene2 = new Scene(root2);

        // Set the initial scene to Screen1
        stage.setScene(scene1);
        stage.show();

        // Add an event handler to switch to Screen2 when a button is clicked
        HelloController.getEllo().setOnAction(e -> {
            stage.setScene(scene2);
        });

        // Add an event handler to switch to Screen1 when a button is clicked
        AIcontroller.getButton().setOnAction(e -> {
            stage.setScene(scene1);
        });

    }

    public static void main(String[] args) {
        launch();
    }
}