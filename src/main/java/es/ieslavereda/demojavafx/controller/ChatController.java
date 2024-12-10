package es.ieslavereda.demojavafx.controller;

import es.ieslavereda.demojavafx.Chat.SalaServidor;
import es.ieslavereda.demojavafx.Chat.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.control.TextFormatter;


import java.net.ServerSocket;
import java.net.URL;
import java.nio.file.attribute.FileAttribute;



public class ChatController {
    @FXML
    private TextField enterChat;
    @FXML
    private TextArea chat;
    @FXML
    private Button btnSend;
    @FXML
    private Button btnStart;
    @FXML
    private TextField userName;
    @FXML
    private TextField host;
    @FXML
    private Circle online;
    @FXML
    private TextField port;

    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Restringe la entrada del campo 'port' a solo números
        port.setTextFormatter(new TextFormatter<>(change -> 
            (change.getControlNewText().matches("\\d*")) ? change : null
        ));
    }
    
    @FXML
    protected void onStartButtonClick(){
        if (userName.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo requerido");
            alert.setHeaderText("Nombre de usuario vacío");
            alert.setContentText("Por favor, ingrese su nombre de usuario antes de continuar.");
            alert.showAndWait();
        }
        else if(host.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo requerido");
            alert.setHeaderText("Host vacío");
            alert.setContentText("Por favor, ingrese la dirección del host.");
            alert.showAndWait();
        }
        else if(port.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo requerido");
            alert.setHeaderText("Port vacío");
            alert.setContentText("Por favor, ingrese el port.");
            alert.showAndWait();
        }
        else {


            Usuario u = new Usuario(host.getText(),Integer.parseInt(port.getText()),enterChat, btnSend, chat, userName.getText());
            u.start();
            if (u.getConnected()){
                btnStart.setDisable(true);
                userName.setDisable(true);
                online.setFill(Paint.valueOf("#3dff21"));
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error Conexion");
                alert.setHeaderText("Ha habido un erro de Conexion");
                alert.setContentText("No se ha podido conectar con el host o el puerto que ha introducido, pruebe otro");
                alert.showAndWait();
            }

        }

    }
    @FXML
    protected void onSendButtonClick(){

    }
}
