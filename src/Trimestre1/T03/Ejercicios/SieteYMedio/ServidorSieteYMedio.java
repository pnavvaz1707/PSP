package Trimestre1.T03.Ejercicios.SieteYMedio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Clase que hará de servidor del CallCenter
 */
public class ServidorSieteYMedio {
    /**
     * Objeto tipo ServerSocket que servirá como servidor para las conexiones
     */
    private ServerSocket servidor;

    /**
     * Puerto donde se alojará el servidor del CallCenter
     */
    private final int PUERTO = 5555;

    /**
     * Número de conexiones actuales
     */
    static int conexionesActuales = 0;

    /**
     * Número de conexiones máximas
     */
    private final int MAXIMO_CONEXIONES = 3;

    /**
     * Lista dinámica de sockets conectados al servidor
     */
    private ArrayList<Socket> conectados = new ArrayList<>();

    /**
     * Método que lanza el servidor
     */
    private void initServer() {
        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Se ha iniciado el servidor");
            JuegoSieteYMedio juegoSieteYMedio = new JuegoSieteYMedio(MAXIMO_CONEXIONES);

            //Se realiza el bucle hasta que se llene el servidor
            while (true) {
                Socket s;
                //Aceptamos la conexión recibida
                s = servidor.accept();
                if (conexionesActuales < MAXIMO_CONEXIONES) {

                    conectados.add(s);

                    conexionesActuales++;

                    Thread hilo = new Thread(new JugadorSieteYMedio(s, juegoSieteYMedio));
                    hilo.start();
                } else {
                    System.err.println("Máximo de jugadores alcanzados");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ServidorSieteYMedio servidorSieteYMedio = new ServidorSieteYMedio();
        servidorSieteYMedio.initServer();
    }
}