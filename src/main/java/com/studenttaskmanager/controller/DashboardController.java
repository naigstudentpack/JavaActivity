package com.studenttaskmanager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import com.studenttaskmanager.utils.DatabaseUtil;
import com.studenttaskmanager.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardController {

    @FXML private Label welcomeLabel;
    @FXML private TextField taskField;
    @FXML private ListView<String> taskListView;

    private String currentUser = "";

    public void initialize() {
        // Get current user from login (you can pass this from LoginController)
        currentUser = LoginController.getLoggedInUser();
        welcomeLabel.setText("Welcome, " + currentUser + "!");
        loadTasks();
    }

    @FXML
    private void addTask() {
        String task = taskField.getText().trim();
        if (task.isEmpty()) {
            showAlert(AlertType.WARNING, "Warning", "Please enter a task!");
            return;
        }

        String sql = "INSERT INTO tasks (username, task) VALUES (?, ?)";

        try (Connection conn = DatabaseUtil.connectToSupabase()) {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, currentUser);
                pstmt.setString(2, task);
                pstmt.executeUpdate();
                pstmt.close();

                taskField.clear();
                loadTasks();
                showAlert(AlertType.INFORMATION, "Success", "Task added!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Failed to add task!");
        }
    }

    @FXML
    private void markTaskDone() {
        String selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask == null) {
            showAlert(AlertType.WARNING, "Warning", "Please select a task!");
            return;
        }

        // Remove the task (mark as done)
        deleteSelectedTask();
        showAlert(AlertType.INFORMATION, "Success", "Task completed! ✅");
    }

    @FXML
    private void deleteTask() {
        String selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask == null) {
            showAlert(AlertType.WARNING, "Warning", "Please select a task!");
            return;
        }

        deleteSelectedTask();
        showAlert(AlertType.INFORMATION, "Success", "Task deleted!");
    }

    private void deleteSelectedTask() {
        String selectedTask = taskListView.getSelectionModel().getSelectedItem();
        String sql = "DELETE FROM tasks WHERE username = ? AND task = ?";

        try (Connection conn = DatabaseUtil.connectToSupabase()) {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, currentUser);
                pstmt.setString(2, selectedTask);
                pstmt.executeUpdate();
                pstmt.close();

                loadTasks();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadTasks() {
        taskListView.getItems().clear();
        String sql = "SELECT task FROM tasks WHERE username = ? ORDER BY id DESC";

        try (Connection conn = DatabaseUtil.connectToSupabase()) {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, currentUser);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    taskListView.getItems().add(rs.getString("task"));
                }

                rs.close();
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() throws Exception {
        Main.changeScene("login.fxml");
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}