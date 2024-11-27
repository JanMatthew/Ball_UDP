package es.ieslavereda.demojavafx.Chat;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class LecturaThread implements Runnable{
    private Socket socket;
    private TextArea textArea;
    public LecturaThread(Socket socket, TextArea textArea) {
        this.socket = socket;
        this.textArea = textArea;
    }
    public void run() {
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String linea;
            while ((linea = in.readLine()) != null) {
                textArea.appendText(linea + "\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
