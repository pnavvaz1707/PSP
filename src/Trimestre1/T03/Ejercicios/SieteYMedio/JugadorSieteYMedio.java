package Trimestre1.T03.Ejercicios.SieteYMedio;

import Trimestre1.T03.Clase.Whatsapp.E8ServidorChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class JugadorSieteYMedio implements Runnable {

    Socket conexion;
    String nombre;
    ArrayList<CartaSieteYMedio> mano;
    JuegoSieteYMedio juegoSieteYMedio;
    boolean haJugado = false;

    /**
     * Stream de lectura de mensajes en la conexión realizada
     */
    DataInputStream entrada;

    /**
     * Stream de escritura de mensajes en la conexión realizada
     */
    DataOutputStream salida;

    public JugadorSieteYMedio(Socket conexion, JuegoSieteYMedio juegoSieteYMedio) {
        this.conexion = conexion;
        this.juegoSieteYMedio = juegoSieteYMedio;
        this.mano = new ArrayList<>();

        try {
            //Recogemos los torrentes de datos y escritura de la conexión
            entrada = new DataInputStream(conexion.getInputStream());
            salida = new DataOutputStream(conexion.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            salida.writeUTF("Introduce un nombre: ");
            nombre = entrada.readUTF();

            juegoSieteYMedio.regitrarse(this);

            juegoSieteYMedio.iniciarJuego(this);

            while (!juegoSieteYMedio.isTerminado()) {
                juegoSieteYMedio.jugar(this);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
