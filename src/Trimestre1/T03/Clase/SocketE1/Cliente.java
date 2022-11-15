package Trimestre1.T03.Clase.SocketE1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    final int PUERTO = 5000;
    Socket cliente;
    String mensaje;
    String mensajeRecibido;
    DataInputStream entrada;
    DataOutputStream salida;
    Scanner sc = new Scanner(System.in);

    public void initClient() {
        try {
            cliente = new Socket("localhost", PUERTO);

            salida = new DataOutputStream(cliente.getOutputStream());
            entrada = new DataInputStream(cliente.getInputStream());
            do {

                System.out.println("Write message.....");
                mensaje = sc.nextLine();

                salida.writeUTF(mensaje);
                System.out.println("Message send");

                mensajeRecibido = entrada.readUTF();
                System.out.println("El servidor dice: " + mensajeRecibido);

            } while (!mensaje.equals("adios"));
            entrada.close();
            salida.close();
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Cliente c = new Cliente();
        c.initClient();
    }
}
