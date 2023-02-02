package Trimestre1.ExamenesAntiguos.Examen1PRSP2223;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Jugador {

    private final int PUERTO = 5555;

    private DataInputStream entrada;

    private String mensajeRecibido = "";

    public static void main(String[] args) {
        try {
            Jugador jugador = new Jugador();
            jugador.iniciarCliente();
            jugador.jugar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void iniciarCliente() throws IOException {
        Socket conexion = new Socket("localhost", PUERTO);

        entrada = new DataInputStream(conexion.getInputStream());

        System.out.println("Cliente iniciado");

        jugar();
    }

    private void jugar() throws IOException {
        while (!(mensajeRecibido.contains("perdido") || mensajeRecibido.contains("ganado"))) {

            mensajeRecibido = entrada.readUTF();
            System.out.println("El servidor dice: " + mensajeRecibido);
        }
    }
}
