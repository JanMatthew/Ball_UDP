package es.ieslavereda.demojavafx.Chat;

import javafx.scene.control.TextField;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class EscrituraThread implements Runnable{
    private Socket socket;
    private TextField textField;
    private String name;
    public EscrituraThread(Socket socket, TextField textField, String name) {
        this.socket = socket;
        this.textField = textField;
        this.name = name;
    }
    public void run() {
        try{
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
            while (true){
                pw.println("["+ name +"]" + textField.getText());
                Thread.sleep(10000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
