package com.example.whereismybus;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SignupController {
    @FXML
    private TextField id;
    @FXML
    private PasswordField pw;
    @FXML
    private TextField name;
    @FXML
    private Button signupBtn;


    public void insertMember() {
        DBUtil db = new DBUtil();
        Connection conn = db.getConnection();

        PreparedStatement pstmt = null;
        String sql = "INSERT INTO users(id,pw,name) VALUES(?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id.getText());
            pstmt.setString(2, pw.getText());
            pstmt.setString(3, name.getText());
            pstmt.executeUpdate();
            System.out.println("삽입 성공!!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("삽입 실패!!");
        }
    }

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
