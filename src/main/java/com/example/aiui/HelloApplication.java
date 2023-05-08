package com.example.aiui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class HelloApplication extends Application {
    User testUser = new User("password","username","email");
    public String name = testUser.getUsername();
    @Override
    public void start(Stage stage) throws IOException {
        data data = new data();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 406);
        stage.setResizable(false);
        stage.setTitle(name);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}