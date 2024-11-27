package es.ieslavereda.demojavafx.Chat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SalaServidor {
    private static final int PUERTO = 50000;
    public SalaServidor() {

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
                System.out.println("Cliente encontrado");
                Thread t = new Thread(new SalaThread(cliente));
                t.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
