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

      // xml 파싱 빌드업
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = null;
      try {
         builder = factory.newDocumentBuilder();
      } catch (ParserConfigurationException e) {
         e.printStackTrace();
      }

      // xml 파일을 document로 파싱하기
      Document document = null;
      try {
         document = builder.parse(String.valueOf(url));
      } catch (SAXException e) {
         e.printStackTrace();
      }

      NodeList response = document.getElementsByTagName("busArrivalList");
      Node node = response.item(0);

      if (node.getNodeType() == Node.ELEMENT_NODE) {
         Element element = (Element) node;
         getTagValue("plateNo1", element);
      }
      Transformer tf = null;
      try {
         tf = TransformerFactory.newInstance().newTransformer();
      } catch (TransformerConfigurationException e) {
         throw new RuntimeException(e);
      }
      tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      tf.setOutputProperty(OutputKeys.INDENT, "yes");
      Writer out = new StringWriter();
      try {
         tf.transform(new DOMSource(document), new StreamResult(out));
      } catch (TransformerException e) {
         throw new RuntimeException(e);
      }
      result.setText(out.toString());



   }

   private void getTagValue(String plateNo1, Element element) {
   }
}

