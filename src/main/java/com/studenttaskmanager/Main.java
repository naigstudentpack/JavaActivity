package com.studenttaskmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 350);
        stage.setTitle("Student Task Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScene(String fxml) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/" + fxml));

        if (fxml.equals("dashboard.fxml")) {
            Scene scene = new Scene(fxmlLoader.load(), 800, 650);
            primaryStage.setScene(scene);
        } else {
            Scene scene = new Scene(fxmlLoader.load(), 400, 350);
            primaryStage.setScene(scene);
        }

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}