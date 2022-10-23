package com.example.whereismybus;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ExplainController {
    @FXML
    private Button goMainBtn;

    public void goMainScene() {
        try {
            Parent nextScene
                    = FXMLLoader.load(getClass().getResource("main-view.fxml"));
            Scene scene = new Scene(nextScene);

            Stage primaryStage = (Stage) goMainBtn.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
