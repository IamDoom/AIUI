module com.example.aiui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.aiui to javafx.fxml;
    exports com.example.aiui;
}