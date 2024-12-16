package es.ieslavereda.demojavafx.Chat;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BallClient implements Runnable{
    private final static int PUERTO = 50000;
    private final static String COD_TEXTO = "UTF-8";
    private final static int MAX_BYTES = 1400;
    private final ImageView ball;
    private static int port;
    private static String host;
    public BallClient(ImageView ball,String host,int port) {
        this.ball = ball;
        this.host = host;
        this.port = port;
    }
    @Override
    public void run(){
        try(DatagramSocket clientSocket = new DatagramSocket();
            InputStreamReader isrStdin = new InputStreamReader(System.in,COD_TEXTO);
            BufferedReader brStdin = new BufferedReader(isrStdin)){
            InetAddress IPServidor = InetAddress.getByName(host);
            byte[] b = "h".getBytes(COD_TEXTO);
            DatagramPacket paqueteEnviado = new DatagramPacket(b,b.length,IPServidor,port);
            clientSocket.connect(IPServidor,port);
            clientSocket.send(paqueteEnviado);

            while (true){
                byte[] datosRecibidos = new byte[MAX_BYTES];
                DatagramPacket packetRecibido = new DatagramPacket(datosRecibidos,datosRecibidos.length);
                clientSocket.receive(packetRecibido);
                String respuesta = new String(packetRecibido.getData(),0,packetRecibido.getLength(),COD_TEXTO);
                Platform.runLater(() -> ball.relocate(Integer.parseInt(respuesta.split(",")[0]),Integer.parseInt(respuesta.split(",")[1])));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
