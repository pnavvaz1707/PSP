package Trimestre1.ExamenesAntiguos.Examen1PRSP2223;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConexionJugador implements Runnable {
    Juego j;
    Socket conexion;
    String nombre;
    DataOutputStream salida;
    double valorCartas = 0;
    char estado = 'S'; // P = Ha perdido, S = Sigue jugando, G = Ha ganado, C = Se planta

    private static int numJugador = 1;


    //Constructor vacío para comparar luego, en caso de que todos se planten, quien tiene el mayor valor
    public ConexionJugador() {

    }

    public ConexionJugador(Juego j, Socket conexion) {
        this.j = j;
        this.conexion = conexion;

        try {
            salida = new DataOutputStream(conexion.getOutputStream());

            this.nombre = "J" + numJugador;
            numJugador++;

            System.out.println("Se ha conectado " + this.nombre);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            j.iniciarJuego(this);
            Thread.sleep(1500);
            while (estado == 'S') {
                j.jugar(this);
                Thread.sleep(3500);
            }
            j.esperarResultado(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
