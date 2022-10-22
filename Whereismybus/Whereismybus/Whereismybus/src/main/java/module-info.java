module com.example.whereismybus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens com.example.whereismybus to javafx.fxml;
    exports com.example.whereismybus;

}