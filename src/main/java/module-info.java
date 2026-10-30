module com.example.holodatabasegui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.holodatabasegui to javafx.fxml;
    exports com.example.holodatabasegui;
}