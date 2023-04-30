module com.example.aiui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aiui to javafx.fxml;
    exports com.example.aiui;
}