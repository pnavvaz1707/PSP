package Trimestre1.T03.Clase.JuegoAdivinanza;

import Trimestre1.T03.Clase.Whatsapp.E8ClienteChat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteAdivinanza {
    private static final int PUERTO = 5555;
    private Socket cliente;

    private String nombre;
    DataInputStream fentrada; // stream de lectura de mensajes
    DataOutputStream fsalida; // stream de escritura de mensajes

    private static Scanner teclado = new Scanner(System.in);

    public ClienteAdivinanza(Socket s, String nombre) {
        cliente = s;
        this.nombre = nombre;
        try {
            fentrada = new DataInputStream(cliente.getInputStream());
            fsalida = new DataOutputStream(cliente.getOutputStream());
            String texto = " > Entra en el Chat ... " + nombre;
            fsalida.writeUTF(texto); // escribe mensaje de entrada
        } catch (IOException e) {
            System.out.println("ERROR DE E/S");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Iniciado el cliente");
            Socket s = new Socket("localhost", PUERTO);
        } catch (IOException e) {
            System.err.println("No ha conectado " + "a");
            System.exit(0);
        }
    }
}