/**
 * Clase Tuber�a del examen de Programaci�n de Servicios y Procesos
 * 
 * Fecha: 30/11/2022
 * 
 * Autor: Enrique Moyano Carballo
 * 
 * Versi�n: 1.0
 * 
 * Esta clase implementa todos los m�todos para el funcionamiento de la simulaci�n del juego de las 7 y media
 * controlando la sincronizaci�n de los hilos (jugadores)
 *  
 */

package Trimestre1.ExamenesAntiguos.ExamenPRSP2223;

import java.util.ArrayList;

public class Tuberia {

	Baraja miBaraja;
	ArrayList<String> partida = new ArrayList<>(); //Arraylist con los jugadores que disputan la partida
	double maximaPuntuacion = 0; //m�xima puntuaci�n obtenida por los jugadores en la partida
	String jugadorMaximaPuntuacion; //nombre del jugador con la m�xima puntuaci�n en la partida
	boolean isPartidaComenzada = false; //sem�foro que indica si todos los jugadores han llegado a la mesa

	Tuberia(Baraja miBaraja) {
		this.miBaraja = miBaraja;
	}

	/**
	 * Método que incorpora a los jugadores a la partida actual
	 * @param jugador - Tipo String - Jugador que se ha incorporado
	 */
	public synchronized void entrarAPartida(String jugador) {
		System.out.println("El " + jugador + " ha entrado en la partida");
		partida.add(jugador); //incorpora al jugador a la partida
		for (int i = 0; i < partida.size(); i++)
			System.out.print("-" + partida.get(i));
		System.out.println("-");
		if (partida.size() == 3) //Si han llegado todos los jugadores la partida puede comenzar
			isPartidaComenzada = true;
		notifyAll();
	}

	
	/**
	 * Método que solicita que una carta sea extra�da de la baraja. Tendr� comportamientos distintos dependiendo del tipo de jugador que sea
	 * @param jugador - tipo String - jugador que solicita la carta
	 * @return valorCarta - tipo double - Valor de la carta extra�da
	 */
	public synchronized double pedirCarta(String jugador) {
		if (!jugador.equals("Croupier")) { //En el caso de que no sea el croupier
			while ((!jugador.equals(partida.get(0))) || (!isPartidaComenzada)) { //Si no es su turno o si no ha comenzado la partida deber� esperar
				try {
					System.out.println(jugador + " no puede sacar una carta fuera de su turno");
					wait();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
			String manoJugador = partida.get(0); //Extraigo al jugador de la primera posici�n
			partida.remove(0);
			String carta = miBaraja.extraerCarta(); //Extraigo la carta de la baraja
			System.out.println("El " + jugador + " ha sacado " + carta);
			double valorCarta = miBaraja.valorarCarta(carta); //Calculo el valor de la carta
			partida.add(manoJugador); //Coloco al jugador en la �ltima posici�n
			notifyAll();
			return valorCarta;
		} else { //En el caso de que sea el croupier
			String carta = miBaraja.extraerCarta(); //Extraigo la carta de la baraja
			System.out.println("El " + jugador + " ha sacado " + carta);
			double valorCarta = miBaraja.valorarCarta(carta); //Calculo el valor de la carta
			return valorCarta;
		}
	}

	/**
	 * Método para obtener la m�xima puntuaci�n de la partida
	 * @return maximaPuntuacion - Tipo double - M�xima puntuaci�n en la partida
	 */
	public double jugadaMaxima() {
		return maximaPuntuacion;
	}

	/**
	 * Método para almacenar la m�xima puntuaci�n de los jugadores
	 * @param puntuacion - Tipo double - puntuaci�n del jugador
	 * @param jugador - Tipo String - jugador que ha obtenido la puntuaci�n
	 */
	public synchronized void comprobarPuntuacion(double puntuacion, String jugador) {
		if (puntuacion > maximaPuntuacion) {
			maximaPuntuacion = puntuacion;
			jugadorMaximaPuntuacion = jugador;
		}
	}
	
	/**
	 * Método que indica qui�n es el ganador de la partida con su puntuaci�n correspondiente
	 */
	public void decirGanador(){
		System.out.println("El ganador es el "+this.jugadorMaximaPuntuacion+" con "+this.maximaPuntuacion+" puntos");
	}

	/**
	 * Método por el que el croupier debe esperar a que todos los jugadores hagan sus jugadas
	 */
	public synchronized void contemplarPartida() {
		while (partida.size() != 0) //mientras que haya jugadores jugando, el croupier no podr� comenzar a jugar
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Método que elimina a los jugadores de la partida actual
	 * @param name - Tipo String - Jugador que se ha ido de la partida
	 */
	public synchronized void salirDePartida(String name) {
		partida.remove(name);
		notifyAll();
	}
}
