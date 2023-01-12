/**
 * Clase HiloServidor del examen de Programaci�n de Servicios y Procesos
 * 
 * Fecha: 30/11/2022
 * 
 * Autor: Enrique Moyano Carballo
 * 
 * Versi�n: 1.0
 * 
 * Esta clase implementa el hilo del servidor de un socket multicliente siguiendo el modelo cliente-servidor para la comunicaci�n de datos
 * 
 */

package Trimestre1.ExamenesAntiguos.ExamenPRSP2223;

import java.io.*;
import java.net.*;

public class HiloServidor extends Thread {
	DataInputStream fentrada;
	DataOutputStream fsalida;
	Socket socket = null;

	public HiloServidor(Socket s) throws IOException {
		socket = s;
		// se crean flujos de entrada y salida
		fsalida = new DataOutputStream(socket.getOutputStream());
		fentrada = new DataInputStream(socket.getInputStream());
	} // fin del constructor

	public void run() {

		System.out.println("COMUNICO CON: " + socket.toString());
		String cadena = "";
		try {
			cadena = fentrada.readUTF(); // obtener cadena
			System.out.println(cadena);
			fsalida.writeUTF("Gracias "+cadena.substring(0,9));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("FIN CON: " + socket.toString());
		try {
			fsalida.close();
			fentrada.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
