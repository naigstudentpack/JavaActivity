package com.studenttaskmanager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import com.studenttaskmanager.utils.DatabaseUtil;
import com.studenttaskmanager.Main;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private static String loggedInUser = "";

    public static String getLoggedInUser() {
        return loggedInUser;
    }

    @FXML
    private void handleLogin() throws Exception {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.WARNING, "Warning", "Please enter username and password!");
            return;
        }

        boolean isValid = DatabaseUtil.validateUser(username, password);

        if (isValid) {
            loggedInUser = username;
            System.out.println("✅ Login successful!");
            Main.changeScene("dashboard.fxml");
        } else {
            showAlert(AlertType.ERROR, "Error", "Invalid username or password!");
        }
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}