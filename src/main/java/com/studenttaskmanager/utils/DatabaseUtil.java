package com.studenttaskmanager.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseUtil {

    private static Connection connection = null;

    public static Connection connectToSupabase() {
        String url = "jdbc:postgresql://aws-1-ap-southeast-1.pooler.supabase.com:5432/postgres?sslmode=require&user=postgres.gecytdcojglvnrztwdog&password=markgian123!";

        System.out.println("Connecting to Supabase...");

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url);
            System.out.println("✅ Connected to Supabase successfully!");

        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }

    public static void testConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT NOW() as current_time");

                if (rs.next()) {
                    System.out.println("✅ Query successful! Server time: " + rs.getString("current_time"));
                }

                rs.close();
                stmt.close();
            } else {
                System.out.println("No active connection to test.");
            }
        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connectToSupabase();
        testConnection();
        closeConnection();
    }
}