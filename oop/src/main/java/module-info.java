module com.wms {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.wms to javafx.fxml;
    exports com.wms;
}
