package es.ieslavereda.demojavafx.Chat;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SalaThread implements Runnable {
    private Socket socket;
    private GestoPrintWriter gestoPrintWriter;
    public SalaThread(Socket socket, GestoPrintWriter gestoPrintWriter) {
        this.socket = socket;
        this.gestoPrintWriter = gestoPrintWriter;
    }


    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            while ((line = br.readLine()) != null) {
                for (PrintWriter printWriter : gestoPrintWriter.getPrintWriterSet()) {
                    printWriter.println(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
