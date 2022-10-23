package com.example.whereismybus;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    @FXML
    private Button signupBtn;
    public void signupScene() {
        try {
            Parent nextScene
                    = FXMLLoader.load(getClass().getResource("signup.fxml"));
            Scene scene = new Scene(nextScene);

            Stage primaryStage = (Stage) signupBtn.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button loginBtn;
    public void loginScene() {
        try {
            Parent nextScene
                    = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(nextScene);

            Stage primaryStage = (Stage) loginBtn.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search() throws IOException {
        try {
            Parent nextScene
                    = FXMLLoader.load(getClass().getResource("sub-view.fxml"));
            Scene scene = new Scene(nextScene);

            Stage primaryStage = (Stage) loginBtn.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button explainBtn;
    public void explainScene() {
        try {
            Parent nextScene
                    = FXMLLoader.load(getClass().getResource("explain.fxml"));
            Scene scene = new Scene(nextScene);

            Stage primaryStage = (Stage) explainBtn.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




