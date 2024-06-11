module com.uaspborestoran {
    requires transitive javafx.graphics;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens com.uaspborestoran.controller to javafx.fxml;
    exports com.uaspborestoran;
    exports com.uaspborestoran.controller;
    exports com.uaspborestoran.data;
}
