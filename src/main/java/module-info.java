module com.example.privatemoviecollection {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.privatemoviecollection to javafx.fxml;
    exports com.example.privatemoviecollection;
}