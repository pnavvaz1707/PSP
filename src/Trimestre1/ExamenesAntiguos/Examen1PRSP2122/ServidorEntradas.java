package Trimestre1.ExamenesAntiguos.Examen1PRSP2122;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEntradas {
    private final int PUERTO = 5555;
    private ServerSocket servidor;
    private final int PLAZAS = 10;
    private int plazasOcupadas = 0;
    Socket[] clientesConectados = new Socket[PLAZAS];

    private void iniciarServidor() throws IOException {
        servidor = new ServerSocket(PUERTO);
        System.out.println("Iniciado el servidor");

        while (true) {
            Socket s;
            s = servidor.accept();

            if (plazasOcupadas < PLAZAS) {
                clientesConectados[plazasOcupadas] = s;

                Thread hilo = new Thread(new HiloTaquilla(s));
                hilo.start();

                plazasOcupadas++;
            } else {
                for (Socket clienteConectado : clientesConectados) {
                    clienteConectado.close();
                }
                s.close();
            }
        }
    }

    public static void main(String[] args) {
        try {
            ServidorEntradas servidorEntradas = new ServidorEntradas();
            servidorEntradas.iniciarServidor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
