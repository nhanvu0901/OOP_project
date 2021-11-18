package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main extends Application {
    private double x, y;
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Scene/FXsceneLogin.fxml"));
            Scene scene = new Scene(root);
//            String css = this.getClass().getResource("style/style.css").toExternalForm();
//            scene.getStylesheets().add(css);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý giáo viên");
            primaryStage.setResizable(false);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            //Giúp kéo thả được màn hình
            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {

                primaryStage.setX(event.getScreenX() - x);
                primaryStage.setY(event.getScreenY() - y);

            });
            primaryStage.show();
        }

        catch(Exception e) {
            e.printStackTrace();
        }

    }



    public static void main(String[] args) {
        launch(args);
    }


}
