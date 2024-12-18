package es.ieslavereda.demojavafx.controller;

import es.ieslavereda.demojavafx.Chat.BallClient;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class UserController {
    @FXML
    private ImageView ball;
    @FXML
    private Button connect;
    @FXML
    private TextField port;
    @FXML
    private TextField host;
    @FXML
    protected void onConnButtonClick(){
        if(host.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo requerido");
            alert.setHeaderText("Host vacío");
            alert.setContentText("Por favor, ingrese la dirección del host.");
            alert.showAndWait();
        }
        else if(port.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo requerido");
            alert.setHeaderText("Port vacío");
            alert.setContentText("Por favor, ingrese el port.");
            alert.showAndWait();
        }
        else {
            Integer puerto = null;
            try {
                puerto = Integer.parseInt(port.getText());
            } catch (Exception e) {

            }
            if (puerto != null) {


                BallClient bc = new BallClient(ball, host.getText(), Integer.parseInt(port.getText()), connect);
                Thread t = new Thread(bc);
                t.start();
                ball.setVisible(true);
                connect.setText("DISCONNECT");
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Campo requerido");
                alert.setHeaderText("Port no es un numero");
                alert.setContentText("Por favor, ingrese el port.");
                alert.showAndWait();
            }
        }
    }
}
