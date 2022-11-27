package Trimestre1.ExamenesAntiguos.ExamenPRSP2021;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Profesor {
    private final int PUERTO = 5555;
    private ServerSocket servidor;

    public static void main(String[] args) {

        try {
            Profesor profesor = new Profesor();
            profesor.iniciarClase();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void iniciarClase() throws IOException {
        servidor = new ServerSocket(PUERTO);
        Conexiones conexiones = new Conexiones();
        System.out.println("Ha empezado la clase");
        while (true) {
            Socket s;
            s = servidor.accept();

            Thread hilo = new Thread(new HiloProfesor(s, conexiones));
            hilo.start();
        }
    }
}
