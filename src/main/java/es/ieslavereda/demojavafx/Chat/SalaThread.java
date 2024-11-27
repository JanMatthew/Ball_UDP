package es.ieslavereda.demojavafx.Chat;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SalaThread implements Runnable {
    private Socket socket;
    public SalaThread(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            while ((line = br.readLine()) != null) {
                pw.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
