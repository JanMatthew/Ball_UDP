package es.ieslavereda.demojavafx;

import es.ieslavereda.demojavafx.controller.ExampleTaskJFXController;
import es.ieslavereda.demojavafx.model.Filosofo;
import es.ieslavereda.demojavafx.model.Tenedor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExampleTaskJFXApplication extends Application {
    static ExampleTaskJFXController controller = new ExampleTaskJFXController();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExampleTaskJFXApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 415, 330);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {


        launch();
    }
}