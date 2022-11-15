package Trimestre1.T03.Clase.SocketE1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    final int PUERTO = 5000;
    ServerSocket servidor;
    Socket cliente;
    DataInputStream entrada;
    DataOutputStream salida;
    String mensajeRecibido;
    String mensaje;
    Scanner sc = new Scanner(System.in);

    public void initServer() {
        try {
            servidor = new ServerSocket(PUERTO);
            cliente = new Socket();

            System.out.println("Waiting connection...");

            cliente = servidor.accept();

            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

            do {
                System.out.println("Client connected");

                mensajeRecibido = entrada.readUTF();
                System.out.println("El cliente dice: " + mensajeRecibido);


                System.out.println("¿Qué respondes?");
                mensaje = sc.nextLine();

                salida.writeUTF(mensaje);
            }while (mensajeRecibido.equalsIgnoreCase("adios"));
            entrada.close();
            salida.close();
            cliente.close();
            servidor.close();
            System.out.println("End connection");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.initServer();
    }
}
