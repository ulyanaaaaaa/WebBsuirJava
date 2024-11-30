package com.example.bd7;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleRegisterButtonAction() {
        String name = nameField.getText().trim();
        String surname = surnameField.getText().trim();
        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();

        if (!isValidName(name) || !isValidName(surname)) {
            messageLabel.setText("Имя и фамилия должны начинаться с большой буквы и содержать не менее 2 букв без цифр.");
            return;
        }

        if (password.length() < 6 || password.contains(" ")) {
            messageLabel.setText("Пароль должен содержать не менее 6 символов и не содержать пробелов.");
            return;
        }

        if (name.isEmpty() || surname.isEmpty() || login.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Все поля должны быть заполнены.");
            return;
        }

        DatabaseService databaseService = new DatabaseService();

        Person person = new Person();
        person.setName(name);
        person.setSurname(surname);

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setPerson(person);

        boolean isRegistered = databaseService.addUser(user);
        if (isRegistered) {
            messageLabel.setText("Регистрация прошла успешно! Теперь вы можете войти.");
        } else {
            messageLabel.setText("Регистрация не удалась. Попробуйте снова.");
        }
    }

    private boolean isValidName(String name) {
        return name.matches("[A-ZА-Я][a-zа-яA-ZА-Я]{1,}") && !name.matches(".*\\d.*");
    }

    @FXML
    private void handleBackButtonAction() {
        try {
            MainApp.showLoginScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
