package es.ieslavereda.demojavafx.Chat;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BallClient implements Runnable{
    private Button connect;
    private Boolean connected = true;
    private final static int PUERTO = 50000;
    private final static String COD_TEXTO = "UTF-8";
    private final static int MAX_BYTES = 1400;
    private final ImageView ball;
    private static int port;
    private static String host;
    public BallClient(ImageView ball,String host,int port,Button connect) {
        this.ball = ball;
        this.host = host;
        this.port = port;
        this.connect = connect;
    }
    @Override
    public void run(){
        try(DatagramSocket clientSocket = new DatagramSocket()){
            InetAddress IPServidor = InetAddress.getByName(host);
            byte[] b = "h".getBytes(COD_TEXTO);
            DatagramPacket paqueteEnviado = new DatagramPacket(b,b.length,IPServidor,port);
            clientSocket.connect(IPServidor,port);
            clientSocket.send(paqueteEnviado);
            connect.setOnAction(e -> {
                try {
                    if(connect.getText().equals("CONNECT")){
                        clientSocket.send(paqueteEnviado);
                    }
                    else {
                        connect.setText("CONNECT");
                        byte[] h = "DISCONNECT".getBytes(COD_TEXTO);
                        DatagramPacket desconectar = new DatagramPacket(h, h.length, IPServidor, port);
                        clientSocket.connect(IPServidor, port);
                        clientSocket.send(desconectar);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            });

            while (true){
                byte[] datosRecibidos = new byte[MAX_BYTES];
                DatagramPacket packetRecibido = new DatagramPacket(datosRecibidos,datosRecibidos.length);
                clientSocket.receive(packetRecibido);
                String respuesta = new String(packetRecibido.getData(),0,packetRecibido.getLength(),COD_TEXTO);
                Platform.runLater(() -> {
                    ball.relocate(Integer.parseInt(respuesta.split(",")[0]),Integer.parseInt(respuesta.split(",")[1]));
                    ball.setRotate(Integer.parseInt(respuesta.split(",")[2]));
                });
            }
        }catch (Exception e){
            e.printStackTrace();
            connected = false;
        }
    }
    public Boolean getConnected(){
        return connected;
    }
}
