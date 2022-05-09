module com.mycompany.finalprj {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.finalprj to javafx.fxml;
    exports com.mycompany.finalprj;
}
