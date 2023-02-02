package Trimestre1.ExamenesAntiguos.Examen1PRSP2223;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    ServerSocket servidor;
    final int PUERTO = 5555;
    final int MAXIMO_JUGADORES = 3;
    static int conexionesActuales = 0;

    private void iniciarServidor() throws IOException {
        servidor = new ServerSocket(PUERTO);
        Juego juego = new Juego(MAXIMO_JUGADORES);
        System.out.println("Servidor iniciado");

        while (true) {
            Socket s = servidor.accept();

            DataOutputStream salida = new DataOutputStream(s.getOutputStream());
            if (conexionesActuales < MAXIMO_JUGADORES) {

                conexionesActuales++;

                Thread hilo = new Thread(new ConexionJugador(juego, s));
                hilo.start();
            } else {
                salida.writeUTF("La partida está llena");
                s.close();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Servidor servidor = new Servidor();
            servidor.iniciarServidor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
