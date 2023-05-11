package com.example.aiui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.io.IOException;



public class HelloController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Pane Base;

    @FXML
    private ToggleButton mode;

    @FXML
    private TextField input;

    @FXML
    private Pane sidebar;

    @FXML
    private Button submit;

    @FXML
    protected void onmodetoggle(ActionEvent event) {
        if (mode.getStyle().equals("-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black;")) {
            mode.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: white;");
            Base.setStyle("-fx-background-color: black;");

            input.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: white;");
            mode.setText("Lightmode");
        }else{
            mode.setStyle("-fx-background-radius: 10; -fx-background-color: white; -fx-border-width: 0; -fx-region-border: 0;");
            Base.setStyle("-fx-background-color: white;");
            mode.setText("Darkmode");
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
