package Trimestre1.T03.Clase.Whatsapp;

import java.io.*;
import java.net.*;

public class E8HiloServidor extends Thread {

	DataInputStream fentrada;
	Socket socket = null;

	public E8HiloServidor(Socket s) {
		socket = s;
		try {
			// CREO FLUJO DE ENTRADA
			fentrada = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("ERROR DE E/S");
			e.printStackTrace();
		}
	}

	public void run() {
		E8ServidorChat.mensaje.setText("N�MERO DE CONEXIONES ACTUALES: "
				+ E8ServidorChat.ACTUALES);
		// al conectarse el cliente le env�o todos los mensajes
		String texto = E8ServidorChat.textarea.getText();
		EnviarMensajes(texto);
		/*
		 * en el siguiente bucle se recibe todo lo que el cliente escribe en el
		 * chat El bot�n salir provoca el env�o de un asterisco (*) al servidor
		 * y �ste se saldr� del bucle
		 */
		while (true) {
			String cadena = "";
			try {
				cadena = fentrada.readUTF(); // lee lo que el cliente escribe
				// control de salida del ciente mediante el env�o de "*"
				if (cadena.trim().equals("*")) {
					E8ServidorChat.ACTUALES--;
					E8ServidorChat.mensaje
							.setText("N�MERO DE CONEXIONES ACTUALES: "
									+ E8ServidorChat.ACTUALES);
					break; // salida del bucle
				}

				E8ServidorChat.textarea.append(cadena + "\n");
				texto = E8ServidorChat.textarea.getText();
				EnviarMensajes(texto); // env�o texto a todos los clientes
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}// fin while
	}// fin run

	private void EnviarMensajes(String texto) {
		int i;
		// recorremos tabla de sockets para enviarles los mensajes
		for (i = 0; i < E8ServidorChat.CONEXIONES; i++) {
			Socket s1 = E8ServidorChat.tabla[i]; // obtiene el socket
			try {
				DataOutputStream fsalida = new DataOutputStream(
						s1.getOutputStream());
				fsalida.writeUTF(texto);// escribe en el socket el texto
			} catch (SocketException se) {
				// excepci�n lanzada cuando se escribe en un sockeet finalizado
			} catch (IOException e) {
				e.printStackTrace();
			}
		}// fin del for
	}// fin del m�todo
}// fin del programa
