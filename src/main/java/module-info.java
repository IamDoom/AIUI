module com.example.aiui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.prefs;
    requires junit;
    requires org.junit.jupiter.api;


    opens com.example.aiui to javafx.fxml;
    exports com.example.aiui;
}