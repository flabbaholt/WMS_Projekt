module com.wms {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive java.sql;

    exports com.wms;
    exports com.wms.controller;
    exports com.wms.model;

    opens com.wms to javafx.fxml;
    opens com.wms.controller to javafx.fxml;
    opens com.wms.model to javafx.base;

    
    
}
