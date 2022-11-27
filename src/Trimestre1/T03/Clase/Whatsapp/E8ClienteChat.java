package Trimestre1.T03.Clase.Whatsapp;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class E8ClienteChat extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	Socket socket = null;
	// streams
	DataInputStream fentrada; // stream de lectura de mensajes
	DataOutputStream fsalida; // stream de escritura de mensajes
	String nombre;
	static JTextField mensaje = new JTextField();
	private JScrollPane scrollpane1;
	static JTextArea textarea1;
	JButton boton = new JButton("Enviar");
	JButton desconectar = new JButton("Salir");
	boolean repetir = true;

	// Constructor
	public E8ClienteChat(Socket s, String nombre) {
		super("CONEXI�N DEL CLIENTE CHAT: " + nombre);
		setLayout(null);
		mensaje.setBounds(10, 10, 400, 30);
		add(mensaje);
		textarea1 = new JTextArea();
		scrollpane1 = new JScrollPane(textarea1);
		scrollpane1.setBounds(10, 50, 400, 300);
		add(scrollpane1);
		boton.setBounds(420, 10, 100, 30);
		add(boton);
		desconectar.setBounds(420, 50, 100, 30);
		add(desconectar);
		textarea1.setEditable(false);
		boton.addActionListener(this);
		desconectar.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		socket = s;
		this.nombre = nombre;
		// creaci�n de flujos
		try {
			fentrada = new DataInputStream(socket.getInputStream());
			fsalida = new DataOutputStream(socket.getOutputStream());
			String texto = " > Entra en el Chat ... " + nombre;
			fsalida.writeUTF(texto); // escribe mensaje de entrada
		} catch (IOException e) {
			System.out.println("ERROR DE E/S");
			e.printStackTrace();
			System.exit(0);
		}
	}// fin del constructor

	// acciones de los botones
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == boton) { // pulsa el bot�n ENVIAR
			String texto = nombre + "> " + mensaje.getText();
			try {
				mensaje.setText(""); // limpio area de mensaje
				fsalida.writeUTF(texto);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == desconectar) { // pulsa el bot�n SALIR
			String texto = " > Abandona el Chat ... " + nombre;
			try {
				fsalida.writeUTF(texto);
				fsalida.writeUTF("*");
				repetir = false; // para salir del bucle
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	// m�todo para leer los mensajes del chat (lo que le manda el hilo)
	public void ejecutar() {

		String texto = "";
		while (repetir) {
			try {
				texto = fentrada.readUTF(); // leer mensajes
				textarea1.setText(texto); // visualiza los mensajes
			} catch (IOException e) {
				// error provocado por el cierre del servidor
				JOptionPane
						.showMessageDialog(
								null,
								"IMPOSIBLE CONECTAR CON EL SERVIDOR\n"
										+ e.getMessage(),
								"<<MENSAJE DE ERROR:2>>",
								JOptionPane.ERROR_MESSAGE);
				repetir = false; // sale del bucle
			}
		}// fin del while
		try {
			socket.close();// cierre del socket
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}// fin de ejecutar

	public static void main(String[] args) {
		int puerto = 44444;
		String nombre = JOptionPane.showInputDialog("Introduce tu nick:");
		Socket s = null;
		try {
			// cliente y servidor se ejecutan en la m�quina local
			s = new Socket("localhost", puerto);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(),
					"<<MENSAJE DE ERROR:1>>", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		if (!nombre.trim().equals("")) {// hay algo que escribir
			E8ClienteChat cliente = new E8ClienteChat(s, nombre);
			cliente.setBounds(0, 0, 540, 400);
			cliente.setVisible(true);
			cliente.ejecutar();
		} else {
			System.out.println("El nombre est� vac�o....");
		}
	}// fin del main
}// fin del programa
