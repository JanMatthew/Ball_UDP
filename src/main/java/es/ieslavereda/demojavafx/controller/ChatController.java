package es.ieslavereda.demojavafx.controller;

import es.ieslavereda.demojavafx.Chat.SalaServidor;
import es.ieslavereda.demojavafx.Chat.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.ServerSocket;

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
    protected void onStartButtonClick(){
        Usuario u = new Usuario(enterChat,btnSend,chat,enterChat.getText());
        u.start();
    }
    @FXML
    protected void onSendButtonClick(){

    }
}
