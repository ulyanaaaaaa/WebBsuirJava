module com.example.bd7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.bd7 to javafx.fxml;
    exports com.example.bd7;
}