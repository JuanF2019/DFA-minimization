module com.example.dfaminimization {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dfaminimization to javafx.fxml;
    exports com.example.dfaminimization;
    exports com.example.dfaminimization.ui;
    opens com.example.dfaminimization.ui to javafx.fxml;
}