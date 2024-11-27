package es.ieslavereda.demojavafx.Chat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Usuario {
    private String host;
    private int port;
    private String name;
    private TextField enterChat;
    private Button send;
    private TextArea chat;

    public Usuario(TextField enterChat, Button send, TextArea chat,String name) {
        this.host = "127.0.0.1";
        this.port = 50000;
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
    public void start() {
        Socket socket = null;
        try {
            socket = new Socket(host,port);
            Thread lectura = new Thread(new LecturaThread(socket,chat));
            lectura.start();
            Thread escritura = new Thread(new EscrituraThread(socket,enterChat,name));
            escritura.start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}