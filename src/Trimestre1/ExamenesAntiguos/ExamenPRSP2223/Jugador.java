/**
 * Clase Jugador del examen de Programaci�n de Servicios y Procesos
 * <p>
 * Fecha: 30/11/2022
 * <p>
 * Autor: Enrique Moyano Carballo
 * <p>
 * Versi�n: 1.0
 * <p>
 * Esta clase simula el comportamiento de los jugadores de la partida de las siete y media (jugadores y Croupier)
 */
package Trimestre1.ExamenesAntiguos.ExamenPRSP2223;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Jugador implements Runnable {
    Tuberia miTuberia;
    Thread t;
    double miPuntuacion = 0.0; //puntuaci�n del jugador en la partida

    /**
     * Constructor
     * @param miTuberia - tipo Tuberia - Objeto con los m�todos accesibles por los jugadores
     * @param nombreJugador - tipo String - Nombre del jugador creado
     */
    Jugador(Tuberia miTuberia, String nombreJugador) {
        this.miTuberia = miTuberia;
        this.t = new Thread(this);
        t.setName(nombreJugador);
    }

    public void run() {
        if (!t.getName().equals("Croupier")) { //Comportamiento de los jugadores que no son el croupier
            miTuberia.entrarAPartida(t.getName());
            while (miPuntuacion < 5.5) { //el jugador se arriesgar� pidiendo cartas hasta alcanzar 5 y media como m�nimo
                System.out.println("El " + t.getName() + " quiere pedir una carta");
                miPuntuacion += miTuberia.pedirCarta(t.getName());
                System.out.println("Puntuaci�n de " + t.getName() + ":" + miPuntuacion);
            }
            if (miPuntuacion < 7.5) { //En caso de plantarse
                System.out.println(t.getName() + " se ha plantado con " + miPuntuacion);
                miTuberia.comprobarPuntuacion(miPuntuacion, t.getName());
            } else //En caso de pasarse de 7 y media
                System.out.println(t.getName() + " se ha pasado");
            miTuberia.salirDePartida(t.getName());
        } else { //Comportamiento del croupier
            miTuberia.contemplarPartida();
            System.out.println("////COMIENZA A JUGAR EL CROUPIER");
            while (miPuntuacion < miTuberia.jugadaMaxima()) { //el croupier deber� arriesgarse hasta igualar el valor m�ximo de la jugada de los jugadores
                System.out.println("El " + t.getName() + " saca una carta");
                miPuntuacion += miTuberia.pedirCarta(t.getName());
                System.out.println("Puntuaci�n de " + t.getName() + ":" + miPuntuacion);
            }
            if (miPuntuacion > 7.5) { //En caso de pasarse de 7 y media
                System.out.println(t.getName() + " se ha pasado");
                miTuberia.decirGanador();
            } else //En caso de superar la mano de los jugadores
                System.out.println(t.getName() + " ha ganado con " + miPuntuacion);
        }
        try {
            crearCliente(t.getName(), miPuntuacion);
        } catch (IOException e) {
            System.out.println("Problemas con el servidor, int�ntelo m�s tarde");
        }
    }

    /**
     * Método para comunicar la puntuaci�n al servidor
     *
     * @param name
     * @param puntuacion
     * @throws IOException
     */
    private void crearCliente(String name, double puntuacion) throws IOException {
        String Host = "localhost"; // Host del servidor
        int Puerto = 6000; // puerto remoto
        System.out.println("Conectando con el servidor del casino...");
        Socket cliente;
        try {
            cliente = new Socket(Host, Puerto);
            // CREO FLUJO DE SALIDA AL SERVIDOR
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

            // CREO FLUJO DE ENTRADA AL SERVIDOR
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            String cadena = name + " ha sacado " + miPuntuacion + " puntos";
            // ENV�O DE MENSAJE AL SERVIDOR
            flujoSalida.writeUTF(cadena); //Comunico la puntuaci�n

            // RECEPCI�N DE MENSAJE DEL SERVIDOR
            System.out.println(flujoEntrada.readUTF()); //Recibo las gracias del servidor

            // CIERRE DE STREAMS Y SOCKETS
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();

        } catch (IOException e) {
            System.out.println("Problemas de conexi�n con el servidor. Vuelva a intentarlo");
        }

    }
}
