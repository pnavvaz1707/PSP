/**
 * Clase BARAJA del examen de Programaci�n de Servicios y Procesos
 * 
 * clase auxiliar baraja que simula los comportamientos de una baraja de cartas espa�ola
 * 
 * Fecha: 30/11/2022
 * 
 * Autor: Enrique Moyano Carballo
 * 
 * Versi�n: 1.0
 */

package Trimestre1.ExamenesAntiguos.ExamenPRSP2223;

import java.util.ArrayList;

public class Baraja {
	ArrayList<String> oros = new ArrayList<String>();
	ArrayList<String> bastos = new ArrayList<String>();
	ArrayList<String> copas = new ArrayList<String>();
	ArrayList<String> espadas = new ArrayList<String>();
	String cartaSacada;

	// Constructor
	Baraja() {
		for (int i = 0; i < 10; i++) {
			if (i < 7)
				oros.add(i, String.valueOf(i + 1) + " de oros");
			else if (i == 7)
				oros.add(i, "Sota de oros");
			else if (i == 8)
				oros.add(i, "Caballo de oros");
			else
				oros.add(i, "Rey de oros");
		}
		for (int i = 0; i < 10; i++) {
			if (i < 7)
				bastos.add(i, String.valueOf(i + 1) + " de bastos");
			else if (i == 7)
				bastos.add(i, "Sota de bastos");
			else if (i == 8)
				bastos.add(i, "Caballo de bastos");
			else
				bastos.add(i, "Rey de bastos");
		}
		for (int i = 0; i < 10; i++) {
			if (i < 7)
				copas.add(i, String.valueOf(i + 1) + " de copas");
			else if (i == 7)
				copas.add(i, "Sota de copas");
			else if (i == 8)
				copas.add(i, "Caballo de copas");
			else
				copas.add(i, "Rey de copas");
		}
		for (int i = 0; i < 10; i++) {
			if (i < 7)
				espadas.add(i, String.valueOf(i + 1) + " de espadas");
			else if (i == 7)
				espadas.add(i, "Sota de espadas");
			else if (i == 8)
				espadas.add(i, "Caballo de espadas");
			else
				espadas.add(i, "Rey de espadas");
		}
	}

	/**
	 * M�todo para extraer cartas de la baraja. Devuelve un String con la carta y el
	 * @return cartaSacada - Tipo String - Carta extra�da
	 */
	public String extraerCarta() {
		int paloBaraja = (int) (Math.random() * 4 + 1);
		if (paloBaraja == 1) {
//			Sale carta de oros
			int carta = (int) (Math.random() * oros.size());
			cartaSacada = oros.get(carta);
			oros.remove(carta);
		} else if (paloBaraja == 2) {
//			Sale carta de bastos
			int carta = (int) (Math.random() * bastos.size());
			cartaSacada = bastos.get(carta);
			bastos.remove(carta);
		} else if (paloBaraja == 3) {
//			Sale carta de espadas
			int carta = (int) (Math.random() * espadas.size());
			cartaSacada = espadas.get(carta);
			espadas.remove(carta);
		} else if (paloBaraja == 4) {
//			Sale carta de copas
			int carta = (int) (Math.random() * copas.size());
			cartaSacada = copas.get(carta);
			copas.remove(carta);
		}
		return cartaSacada;
	}
	
	/**
	 * M�todo para obtener el valor de la carta extra�da
	 * 
	 * @param carta - Tipo String - carta extra�da
	 * @return valor - Tipo Double - Valor de la carta extra�da
	 */
	public double valorarCarta(String carta) {
		String valor = carta.substring(0, 1);
		try {
			if (Integer.parseInt(valor) < 8)
				return Double.parseDouble(valor);
		} catch (NumberFormatException e) {
			return 0.5;
		}
		return Double.parseDouble(valor);
	}
}
