package com.example.bd7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SearchViewController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, Integer> userIdColumn;

    @FXML
    private TableColumn<User, String> loginColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> surnameColumn;

    @FXML
    private Label resultLabel;

    @FXML
    private void initialize() {
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
    }

    @FXML
    private void handleSearchUser() {
        String login = searchField.getText().trim();
        if (login.isEmpty()) {
            resultLabel.setText("Введите логин для поиска.");
            return;
        }

        DatabaseService databaseService = new DatabaseService();
        User user = databaseService.getUserByLogin(login);
        if (user != null) {
            ObservableList<User> userList = FXCollections.observableArrayList(user);
            userTable.setItems(userList);
            resultLabel.setText("Пользователь найден:");
        } else {
            resultLabel.setText("Пользователь не найден.");
            userTable.getItems().clear();
        }
    }

    @FXML
    private void handleBackButtonAction() {
        try {
            Stage stage = (Stage) searchField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/bd7/MainView.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
