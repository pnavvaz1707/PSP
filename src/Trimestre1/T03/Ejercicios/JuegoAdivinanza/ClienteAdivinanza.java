package Trimestre1.T03.Ejercicios.JuegoAdivinanza;

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

//    public ClienteAdivinanza(Socket s, String nombre) {
//        cliente = s;
//        this.nombre = nombre;
//        try {
//            fentrada = new DataInputStream(cliente.getInputStream());
//            fsalida = new DataOutputStream(cliente.getOutputStream());
//            fentrada.readUTF();
//            int adivinanza = teclado.nextInt();
//            fsalida.writeInt(adivinanza);
//        } catch (IOException e) {
//            System.out.println("ERROR DE E/S");
//            e.printStackTrace();
//            System.exit(0);
//        }
//    }

    public void initClient() {
        try {
            cliente = new Socket("localhost", PUERTO);

            fsalida = new DataOutputStream(cliente.getOutputStream());
            fentrada = new DataInputStream(cliente.getInputStream());

            do {
                String mensajeRecibido = fentrada.readUTF();
                System.out.println("El servidor dice: " + mensajeRecibido);

                int adivinanza = teclado.nextInt();

                fsalida.writeInt(adivinanza);
                System.out.println("Adivinanza enviada");

                mensajeRecibido = fentrada.readUTF();
                System.out.println("El servidor dice: " + mensajeRecibido);
            } while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        try {
        System.out.println("Iniciado el cliente");
//            Socket s = new Socket("localhost", PUERTO);
        ClienteAdivinanza c = new ClienteAdivinanza();
        c.initClient();
//        } catch (IOException e) {
//            System.err.println("No ha conectado " + "a");
//            System.exit(0);
//        }
    }
}
