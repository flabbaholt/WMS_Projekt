module com.wms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.wms;
    exports com.wms.controller;

    opens com.wms to javafx.fxml;
    opens com.wms.controller to javafx.fxml;
    
    
}
