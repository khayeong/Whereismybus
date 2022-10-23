package com.example.whereismybus;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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


public class SubViewController {

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

   @FXML
   private Button resultBtn;
   @FXML
   private Text result;
   String data;
   @FXML
   private TextField stationId;

   public void search() throws IOException {

      data = stationId.getText();

      StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6410000/busarrivalservice/getBusArrivalList"); /*URL*/
      urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=xW4atbXtoEtXJMPCdN6ZwC%2FPLFB8W9oHzOVkc3LWxC1BDjP4i3ZNj8VWE229j5NkLaP5JWcwvS0hdQYLrZa%2BJQ%3D%3D"); /*Service Key*/
      urlBuilder.append("&" + URLEncoder.encode("stationId", "UTF-8") + "=" + URLEncoder.encode(data, "UTF-8")); /*정류소ID*/
      URL url = new URL(urlBuilder.toString());
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Content-type", "application/json");
      System.out.println("Response code: " + conn.getResponseCode());
      BufferedReader rd;
      if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      } else {
         rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
      }
      StringBuilder sb = new StringBuilder();
      String line;
      while ((line = rd.readLine()) != null) {
         sb.append(line);
      }
      rd.close();
      conn.disconnect();
      System.out.println(sb.toString());

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = null;
      try {
         builder = factory.newDocumentBuilder();
      } catch (ParserConfigurationException e) {
         e.printStackTrace();
      }

      Document document = null;
      try {
         document = builder.parse(String.valueOf(url));
      } catch (SAXException e) {
         e.printStackTrace();
      }

      // root 요소 가져오기
      Element root = document.getDocumentElement();
      // root 요소 확인 : 첫 태그 sample
      //System.out.println(root.getNodeName());
      // root 요소의 첫번째 노드는 #Text
      Node node = root.getElementsByTagName("busArrivalList").item(0);
      // 다음 노드는 customer
      //Node customer = firstNode.getNextSibling();
      // customer 요소 안의 노드 리스트
      NodeList childList = node.getChildNodes();

      for (int i = 0; i < childList.getLength(); i++) {
         Node item = childList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) { // 노드의 타입이 Element일 경우(공백이 아닌 경우)
               System.out.println(item.getTextContent());
            } else {
               result.setText("공백입니다.");
            }
         }
      }
   }

