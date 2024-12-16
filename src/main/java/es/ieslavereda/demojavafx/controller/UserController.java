package es.ieslavereda.demojavafx.controller;

import es.ieslavereda.demojavafx.Chat.BallClient;
import javafx.fxml.FXML;
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
        ball.setVisible(true);
        connect.setVisible(false);

        BallClient bc = new BallClient(ball,host.getText(),Integer.parseInt(port.getText()));
        Thread t = new Thread(bc);
        t.start();
    }
}
