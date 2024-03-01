module com.example.jatinlab2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.jatinlab2 to javafx.fxml;
    exports com.example.jatinlab2;
}