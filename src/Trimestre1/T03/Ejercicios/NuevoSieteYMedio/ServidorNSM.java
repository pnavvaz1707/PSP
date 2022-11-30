package Trimestre1.T03.Ejercicios.NuevoSieteYMedio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorNSM {
    ServerSocket servidor;
    final int PUERTO = 5555;
    final int MAXIMO_JUGADORES = 3;
    static int conexionesActuales = 0;

    private void iniciarServidor() throws IOException {
        servidor = new ServerSocket(PUERTO);
        JuegoNSM juegoNSM = new JuegoNSM(MAXIMO_JUGADORES);
        System.out.println("Servidor iniciado");

        while (true) {
            Socket s = servidor.accept();
            if (conexionesActuales < MAXIMO_JUGADORES) {

                conexionesActuales++;

                Thread hilo = new Thread(new ConexionJugadorNSM(juegoNSM, s));
                hilo.start();
            } else {
                System.err.println("No cabe");
            }
        }
    }

    public static void main(String[] args) {
        try {
            ServidorNSM servidorNSM = new ServidorNSM();
            servidorNSM.iniciarServidor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
