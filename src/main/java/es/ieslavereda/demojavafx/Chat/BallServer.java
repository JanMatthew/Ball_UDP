package es.ieslavereda.demojavafx.Chat;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class BallServer implements Runnable{

    private static int x=215,y=113,deg=0,velX=2,velY=2,lastVelX,lastVelY;
    private final static int MAX_BYTES = 1400;
    private final static String COD_TEXTO = "UTF-8";
    private final static int PUERTO = 50000;
    private DatagramSocket serverSocket;
    private static Set<InetSocketAddress> clientes = new HashSet<>();
    private static ImageView ball;
    private static Button play;

    public BallServer(Button play,ImageView ball) {
        this.ball = ball;
        this.play = play;
    }
    @Override
    public void run() {
        try(DatagramSocket serverSocket = new DatagramSocket(PUERTO)){
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(String.valueOf(x) + "," + String.valueOf(y) + "," + String.valueOf(deg));
                        Platform.runLater(() -> {
                            ball.relocate(x, y);
                            ball.setRotate(deg);
                        });
                        String pos = String.valueOf(x) + "," + String.valueOf(y) + "," + String.valueOf(deg);
                        byte[] b = String.valueOf(pos).getBytes(COD_TEXTO);
                        for(InetSocketAddress cliente : clientes){
                            DatagramPacket packetSpam = new DatagramPacket(b,
                                    b.length,
                                    cliente.getAddress(),
                                    cliente.getPort());
                            serverSocket.send(packetSpam);

                        }
                        Thread.sleep(10);
                        mover();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            play.setOnAction(e -> {
                if (play.getText().equals("stop")){
                    lastVelX = velX;
                    lastVelY = velY;
                    velX = 0;
                    velY = 0;
                    play.setText("play");
                }
                else{
                    velX = lastVelX;
                    velY = lastVelY;
                    play.setText("stop");
                }

            });
            while (true){
                System.out.println("Esperando usuario...");
                byte[] datosRecibidos = new byte[MAX_BYTES];
                DatagramPacket packetRecibido = new DatagramPacket(datosRecibidos,datosRecibidos.length);
                serverSocket.receive(packetRecibido);
                String mensaje = new String(packetRecibido.getData(),0,packetRecibido.getLength(),COD_TEXTO);
                System.out.printf("mensaje: %s",mensaje);
                if(mensaje.equals("DISCONNECT")){
                    System.out.println(clientes.remove(new InetSocketAddress(packetRecibido.getAddress(),packetRecibido.getPort())));
                }
                else {
                    System.out.println("Usuario recibido");
                    clientes.add(new InetSocketAddress(packetRecibido.getAddress(), packetRecibido.getPort()));
                    System.out.println("Empezando ataque");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void mover(){
        x += velX;
        y += velY;

        if (x <= 0 || x >= 598-ball.getFitWidth()) {
            velX *= -1; // Cambia la dirección horizontal
            x += velX;

        }
        if (y <= 0 || y >= 375-ball.getFitHeight()) {
            velY *= -1; // Cambia la dirección vertical
            y += velY;
        }
        if(velX != 0 ){
            deg += velX > 0 ? 1 : -1;
        }
    }

}
