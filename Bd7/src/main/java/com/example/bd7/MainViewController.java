package com.example.bd7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


import java.util.Comparator;
import java.util.List;

public class MainViewController {

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
    private void initialize() {
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
    }

    @FXML
    private void handleViewAllUsers() {
        DatabaseService databaseService = new DatabaseService();
        List<User> users = databaseService.getAllUsers();
        users.sort(Comparator.comparingInt(User::getUserId).reversed()); // Сортировка по ID в обратном порядке
        ObservableList<User> userList = FXCollections.observableArrayList(users);
        userTable.setItems(userList);
    }

    @FXML
    private void handleUpdateInfo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bd7/UpdateInfoView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Редактирование информации");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSearchUser() {
        try {
            Stage stage = (Stage) userTable.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/bd7/SearchView.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteAccount() {
        DatabaseService databaseService = new DatabaseService();
        User currentUser = databaseService.getCurrentUser();
        boolean isDeleted = databaseService.deleteUser(currentUser);

        if (isDeleted) {
            try {
                Stage stage = (Stage) userTable.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/bd7/Login.fxml"));
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Ошибка при удалении учетной записи.");
        }
    }

    @FXML
    private void handleLogout() {
        try {
            Stage stage = (Stage) userTable.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/bd7/Login.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
