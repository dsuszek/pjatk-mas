module pjatk.finalproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens pjatk.finalproject to javafx.fxml;
    exports pjatk.finalproject;
    exports pjatk.finalproject.controller;
    opens pjatk.finalproject.controller to javafx.fxml;
    exports pjatk.finalproject.model;
    opens pjatk.finalproject.model to javafx.fxml;
}