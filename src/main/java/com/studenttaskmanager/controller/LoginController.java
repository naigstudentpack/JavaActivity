package com.studenttaskmanager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import com.studenttaskmanager.utils.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println("Attempting login for: " + username);

        try (Connection conn = DatabaseUtil.connectToSupabase()) {
            if (conn != null) {
                String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    System.out.println("✅ Login successful!");
                } else {
                    System.out.println("❌ Invalid username or password!");
                }

                rs.close();
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}