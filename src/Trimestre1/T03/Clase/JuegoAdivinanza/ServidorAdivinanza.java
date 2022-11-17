package Trimestre1.T03.Clase.JuegoAdivinanza;

import Trimestre1.T03.Clase.Whatsapp.E8HiloServidor;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServidorAdivinanza {
    private ServerSocket servidor;
    private final int PUERTO = 5555;
    private int conexionesActuales = 0;
    private final int MAXIMO_CONEXIONES = 3;

    private Socket[] conectados = new Socket[MAXIMO_CONEXIONES];

    private void initServer() {
        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Se ha iniciado el servidor");
            JuegoAdivinanza juegoAdivinanza = new JuegoAdivinanza();

            while (conexionesActuales < MAXIMO_CONEXIONES) {
                Socket s;

                s = servidor.accept();

                System.out.println("Cliente conectado");

                conectados[conexionesActuales] = s;

                conexionesActuales++;

                Thread hilo = new Thread(new HiloAdivinanza(juegoAdivinanza, s, this));
                hilo.start();
            }
            if (!servidor.isClosed()) {
                try {
                    System.err.println("M�XIMO N� DE CONEXIONES ESTABLECIDAS: " + conexionesActuales);
                    servidor.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
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

    public int getConexionesActuales() {
        return conexionesActuales;
    }

    public void setConexionesActuales(int conexionesActuales) {
        this.conexionesActuales = conexionesActuales;
    }

    public Socket[] getConectados() {
        return conectados;
    }

    public void setConectados(Socket[] conectados) {
        this.conectados = conectados;
    }
}