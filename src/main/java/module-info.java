module com.demo3.fxml_3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.demo3.fxml_3 to javafx.fxml;
    exports com.demo3.fxml_3;
}