package es.ieslavereda.demojavafx.Chat;

import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SalaServidor {
    private static final int PUERTO = 50000;
    private GestoPrintWriter gestoPrintWriter;
    public SalaServidor() {
        this.gestoPrintWriter = new GestoPrintWriter();
    }

    public static void main(String[] args) {
        SalaServidor sala = new SalaServidor();
        sala.start();
    }
    public void start(){
        try{
            ServerSocket socket = new ServerSocket(PUERTO);

            while (true){
                Socket cliente = socket.accept();
                synchronized (gestoPrintWriter){
                    gestoPrintWriter.addPrintWriter(new PrintWriter(cliente.getOutputStream(), true));
                }
                System.out.println("Cliente encontrado");
                Thread t = new Thread(new SalaThread(cliente,gestoPrintWriter));
                t.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
