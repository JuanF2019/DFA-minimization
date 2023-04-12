package com.example.dfaminimization;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DFAMinimizerApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DFAMinimizerApp.class.getResource("main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("DFA Minimizer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}