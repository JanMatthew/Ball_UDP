package es.ieslavereda.demojavafx.Chat;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Usuario {
    private Boolean connected = true;
    private String host;
    private int port;
    private String name;
    private TextField enterChat;
    private Button send;
    private TextArea chat;
    private PrintWriter pw;

    public Usuario(String host,int port,TextField enterChat, Button send, TextArea chat,String name) {
        this.host = host;
        this.port = port;
        this.name = name;
        this.enterChat = enterChat;
        this.send = send;
        this.chat = chat;
    }

//    public static void main(String[] args) {
//        Usuario usuario = new Usuario();
//        usuario.start();
//    }
    public String mensaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca un mensaje");
        return sc.nextLine();
    }
    public Boolean getConnected(){
        return connected;
    }
    public void start() {
        Socket socket = null;
        try {
            socket = new Socket(host,port);
            Thread lectura = new Thread(new LecturaThread(socket,chat));
            lectura.start();
            try {
                pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
                send.setOnAction(event -> {
                    String mensajeTexto = enterChat.getText().trim();
                    if (!mensajeTexto.isEmpty()) {
                        String mensaje = "[" + name + "] " + mensajeTexto;
                        pw.println(mensaje);
                        enterChat.clear();
                    }
                });
            } catch (IOException e) {

                e.printStackTrace();
            }
        }catch (IOException e){
            connected = false;

            e.printStackTrace();
        }
    }
}
