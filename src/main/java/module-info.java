module com.example.medisoft {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires fontawesomefx;

    requires java.sql;
    requires mysql.connector.j;

    opens com.example.medisoft to javafx.fxml;
    exports com.example.medisoft;
}