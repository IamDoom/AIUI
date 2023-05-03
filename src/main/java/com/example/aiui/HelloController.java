package com.example.aiui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;


public class HelloController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button hello;

    @FXML
    private VBox box;
    @FXML
    private Label welcomeText;
    @FXML
    private ToggleButton mode;

    @FXML
    protected void onmodetoggle() {
        if (mode.getStyle().equals("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black;")) {
            mode.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: white;");
            box.setStyle("-fx-background-color: black;");
            welcomeText.setStyle("-fx-background-color: black; -fx-text-fill: white;");
            hello.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: white;");
            mode.setText("lightmode");
        }else{
            mode.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black;");
            box.setStyle("-fx-background-color: white;");
            welcomeText.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            hello.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black;");
            mode.setText("darkmode");


        }
    }
    @FXML
    public void switchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Ai.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
