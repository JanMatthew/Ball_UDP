package es.ieslavereda.demojavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserXApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(ServerXApplication.class.getResource("bolaUser.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        stage.setTitle("User");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
