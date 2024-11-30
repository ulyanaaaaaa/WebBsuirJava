package com.example.bd7;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;


import java.util.List;

public class LoginController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleLoginButtonAction() {
        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();

        if (login.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Все поля должны быть заполнены.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (password.length() < 6 || password.contains(" ")) {
            messageLabel.setText("Пароль должен содержать не менее 6 символов и не содержать пробелов.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        DatabaseService databaseService = new DatabaseService();
        List<User> users = databaseService.getAllUsers();
        User currentUser = null;
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                currentUser = user;
                break;
            }
        }
        if (currentUser != null) {
            messageLabel.setText("Авторизация пройдена успешно! Добро пожаловать " +
                    currentUser.getPerson().getSurname() + " " + currentUser.getPerson().getName());
            messageLabel.setStyle("-fx-text-fill: green;");

            databaseService.setCurrentUser(currentUser);  // Устанавливаем текущего пользователя

            try {
                Stage stage = (Stage) loginField.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/bd7/MainView.fxml"));
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("Неверный логин или пароль. Попробуйте снова.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void handleRegisterButtonAction() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/bd7/Register.fxml"));
            Stage stage = (Stage) loginField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
