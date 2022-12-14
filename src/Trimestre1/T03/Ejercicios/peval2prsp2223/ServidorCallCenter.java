package Trimestre1.T03.Ejercicios.peval2prsp2223;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Clase que hará de servidor del CallCenter
 */
public class ServidorCallCenter {
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
     * Stream de escritura de mensajes en la conexión realizada
     */
    private DataOutputStream salida;

    /**
     * Lista del tipo de consultas que permite el servidor
     */
    static final String[] tiposConsultas = {
            "Futurología",
            "Meeting",
            "Compras"
    };

    /**
     * Método que lanza el servidor
     */
    private void initServer() {
        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Se ha iniciado el servidor");

            //Se realiza el bucle hasta que se llene el servidor
            while (true) {
                Socket s;
                //Aceptamos la conexión recibida
                s = servidor.accept();

                //Si se ha alcanzado el número máximo de conexiones de clientes, le cerramos la conexión y se lo informamos
                if (conexionesActuales < MAXIMO_CONEXIONES) {
                    System.out.println("Cliente conectado");
                    System.out.println("Inet adress: " + s.getInetAddress());
                    System.out.println("Local port: " + s.getLocalPort());
                    System.out.println("Local adress: " + s.getLocalAddress());

                    //Sumamos uno al número de conexiones actuales
                    conexionesActuales++;

                    Thread hilo = new Thread(new HiloServidorCallCenter(s));
                    hilo.start();
                } else {
                    salida = new DataOutputStream(s.getOutputStream());

                    salida.writeUTF("El servidor está lleno");
                    s.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        ServidorCallCenter servidorAdivinanza = new ServidorCallCenter();
        servidorAdivinanza.initServer();
    }
}