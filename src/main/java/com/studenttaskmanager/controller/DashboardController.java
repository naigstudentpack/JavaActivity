package com.studenttaskmanager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    public void initialize() {
        // ✅ Called automatically when FXML is loaded
        welcomeLabel.setText("Welcome to Dashboard!");
    }
}
