package es.ieslavereda.demojavafx.controller;

import es.ieslavereda.demojavafx.Chat.BallServer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ServerController {
    @FXML
    private ImageView ball;
    @FXML
    private Button play;
    @FXML
    protected void onPlayButtonClick(){
        play.setText("stop");
        BallServer bs = new BallServer(play,ball);
        Thread t = new Thread(bs);
        t.start();
    }
//        BallServer bs = new BallServer(play,ball);
//        bs.start();

}