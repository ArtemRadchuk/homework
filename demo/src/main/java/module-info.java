module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com.example to javafx.fxml;
    exports com.example;
    opens com.example.controller to javafx.fxml;
    exports com.example.controller;

}