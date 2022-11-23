package Trimestre1.T03.Ejercicios.JuegoAdivinanza;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorAdivinanza {
    private ServerSocket servidor;
    private final int PUERTO = 5555;
    private static int conexionesActuales = 0;
    private static final int MAXIMO_CONEXIONES = 3;
    private static ArrayList<Socket> conectados = new ArrayList<Socket>(MAXIMO_CONEXIONES);
    private DataOutputStream salida;

    private void initServer() {
        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Se ha iniciado el servidor");
            JuegoAdivinanza juegoAdivinanza = new JuegoAdivinanza();

            while (true) {
                Socket s;
                s = servidor.accept();

                if (conexionesActuales < MAXIMO_CONEXIONES) {
                    System.out.println("Jugador conectado!");

                    conectados.add(s);

                    conexionesActuales++;

                    Thread hilo = new Thread(new HiloAdivinanza(juegoAdivinanza, s));
                    hilo.start();

                } else {
                    salida = new DataOutputStream(s.getOutputStream());
                    salida.writeUTF("El servidor está lleno");
                    s.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ServidorAdivinanza servidorAdivinanza = new ServidorAdivinanza();
        servidorAdivinanza.initServer();
    }

    public static ArrayList<Socket> getConectados() {
        return conectados;
    }
}