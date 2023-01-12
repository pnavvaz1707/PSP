/**
 * Clase Principal del examen de Programaci�n de Servicios y Procesos
 * <p>
 * Fecha: 30/11/2022
 * <p>
 * Autor: Enrique Moyano Carballo
 * <p>
 * Versi�n: 1.0
 */
package Trimestre1.ExamenesAntiguos.ExamenPRSP2223;

public class Principal {

    public static void main(String[] args) {
        Baraja miBaraja = new Baraja();
        Tuberia miTuberia = new Tuberia(miBaraja);
        String nombreJugador;

        for (int i = 0; i < 10; i++) { // Creo cuatro jugadores (3 jugadores y un croupier)
            if (i == 3) {
                nombreJugador = "Croupier";
            } else {
                nombreJugador = "Jugador " + i;
            }

            if (i < 4) {
                System.out.println("Hilos activos: " + Thread.activeCount());
                Thread t = new Thread(new Jugador(miTuberia, nombreJugador));
                t.start();
            } else {
                System.out.println(nombreJugador + " No puede jugar esta partida");
            }
        }
    }
}
