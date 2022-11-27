package Trimestre1.T03.Ejercicios.NuevoSieteYMedio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloServidorNSM implements Runnable {

    /*
    Escribe
    Lee
     */


    JuegoNSM j;
    Socket conexion;
    String nombre;
    DataInputStream entrada;
    DataOutputStream salida;
    double valorCartas = 0;
    char estado = 'S'; // L = Ha perdido, S = Sigue jugando, W = Ha ganado, C = Se planta

    public HiloServidorNSM(JuegoNSM j, Socket conexion) {
        this.j = j;
        this.conexion = conexion;

        try {
            entrada = new DataInputStream(conexion.getInputStream());
            salida = new DataOutputStream(conexion.getOutputStream());

            salida.writeUTF("Introduce nombre: ");
            this.nombre = entrada.readUTF();

            System.out.println("Se ha conectado " + this.nombre);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            j.iniciarJuego(this);
            while (estado == 'S') {
                j.jugar(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
