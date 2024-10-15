module es.ieslavereda.demojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;


    opens es.ieslavereda.demojavafx to javafx.fxml;
    exports es.ieslavereda.demojavafx;
    exports es.ieslavereda.demojavafx.model;
    opens es.ieslavereda.demojavafx.model to javafx.fxml;
    exports es.ieslavereda.demojavafx.controller;
    opens es.ieslavereda.demojavafx.controller to javafx.fxml;
}