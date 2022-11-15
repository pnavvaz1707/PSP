package Trimestre1.T03.Clase.Whatsapp;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class E8ServidorChat extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	static ServerSocket servidor;
	static final int PUERTO = 44444; // puerto por el que escucha
	static int CONEXIONES = 0; // cuenta de las conexiones
	static int ACTUALES = 0; // n� de conexiones actuales activas
	static int MAXIMO = 20; // m�ximo de conexiones permitidas

	static JTextField mensaje = new JTextField("");
	static JTextField mensaje2 = new JTextField("");
	private JScrollPane scrollpane1;
	static JTextArea textarea;
	JButton salir = new JButton("Salir");
	static Socket tabla[] = new Socket[MAXIMO]; // almacena sockets de clientes

	// Constructor
	public E8ServidorChat() {
		super(" VENTANA DEL SERVIDOR DE CHAT ");
		setLayout(null);

		mensaje.setBounds(10, 10, 400, 30);
		add(mensaje);
		mensaje.setEditable(false);

		mensaje2.setBounds(10, 348, 400, 30);
		add(mensaje2);
		mensaje2.setEditable(false);

		textarea = new JTextArea();
		scrollpane1 = new JScrollPane(textarea);

		scrollpane1.setBounds(10, 50, 400, 300);
		add(scrollpane1);
		salir.setBounds(420, 10, 100, 30);
		add(salir);

		textarea.setEditable(false);
		salir.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == salir) { // SE PULSA SALIR
			try {
				servidor.close(); // cierro servidor
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		System.exit(0); // fin
	}

	public static void main(String[] args) throws IOException {
		servidor = new ServerSocket(PUERTO);
		System.out.println("Servidor iniciado...");
		E8ServidorChat pantalla = new E8ServidorChat();
		pantalla.setBounds(0, 0, 540, 400);
		pantalla.setVisible(true);
		mensaje.setText("N�MERO DE CONEXIONES ACTUALES: " + 0);

		// SE ADMITEN 10 CONEXIONES M�XIMO
		while (CONEXIONES < MAXIMO) {
			Socket s = new Socket();
			try {
				s = servidor.accept();// esperando cliente
			} catch (SocketException ns) {
				// Salimos por aqu� si pulsamos bot�n salir
				// No se ejecuta todo el bucle
				break; // salir del bucle
			}
			tabla[CONEXIONES] = s; // almacena el socket
			CONEXIONES++;
			ACTUALES++;
			E8HiloServidor hilo = new E8HiloServidor(s);
			hilo.start(); // lanza el hilo
		}// fin del bucle
		/*
		 * Sale del bucle anterior si ha habido 10 conexiones o si se pulsa el
		 * bot�n Salir. Cuando finaliza bucle cerrar servidor si no se ha
		 * cerrado antes
		 */
		if (!servidor.isClosed()) {
			try {
				mensaje2.setForeground(Color.red);
				mensaje2.setText("M�XIMO N� DE CONEXIONES ESTABLECIDAS: "
						+ CONEXIONES);
				servidor.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Servidor finalizado...");
	} // main
} // Fin de programa
