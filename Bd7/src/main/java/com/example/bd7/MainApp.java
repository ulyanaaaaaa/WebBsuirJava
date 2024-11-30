package com.example.bd7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainApp.primaryStage = primaryStage;
        showLoginScreen();
    }

    public static void showLoginScreen() throws Exception {
        Parent loginRoot = FXMLLoader.load(MainApp.class.getResource("/com/example/bd7/Login.fxml"));
        primaryStage.setTitle("Авторизация");
        primaryStage.setScene(new Scene(loginRoot, 1000, 500));
        primaryStage.show();
    }

    public static void showRegisterScreen() throws Exception {
        Parent registerRoot = FXMLLoader.load(MainApp.class.getResource("/com/example/bd7/Register.fxml"));
        primaryStage.setTitle("Регистрация");
        primaryStage.setScene(new Scene(registerRoot, 1000, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
