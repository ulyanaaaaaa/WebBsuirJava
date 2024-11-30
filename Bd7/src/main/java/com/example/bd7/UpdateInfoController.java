package com.example.bd7;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateInfoController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label messageLabel;

    private User currentUser;

    @FXML
    private void initialize() {
        DatabaseService databaseService = new DatabaseService();
        this.currentUser = databaseService.getCurrentUser();
        if (currentUser != null) {
            nameField.setText(currentUser.getPerson().getName());
            surnameField.setText(currentUser.getPerson().getSurname());
            loginField.setText(currentUser.getLogin());
            passwordField.setText(currentUser.getPassword());
        }
    }

    @FXML
    private void handleSaveButtonAction() {
        String name = nameField.getText().trim();
        String surname = surnameField.getText().trim();
        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();

        if (name.isEmpty() || surname.isEmpty() || login.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Все поля должны быть заполнены.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        currentUser.getPerson().setName(name);
        currentUser.getPerson().setSurname(surname);
        currentUser.setLogin(login);
        currentUser.setPassword(password);

        DatabaseService databaseService = new DatabaseService();
        boolean isUpdated = databaseService.updateUser(currentUser);

        if (isUpdated) {
            messageLabel.setText("Информация успешно обновлена!");
            messageLabel.setStyle("-fx-text-fill: green;");
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        } else {
            messageLabel.setText("Ошибка при обновлении информации. Попробуйте снова.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void handleCancelButtonAction() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
