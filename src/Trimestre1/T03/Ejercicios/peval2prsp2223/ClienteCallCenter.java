package Trimestre1.T03.Ejercicios.peval2prsp2223;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteCallCenter {
    private static final int PUERTO = 5555;
    private Socket cliente;

    DataInputStream fentrada; // stream de lectura de mensajes
    DataOutputStream fsalida; // stream de escritura de mensajes

    String mensajeRecibido;
    String mensajeAEnviar;

    private static Scanner teclado = new Scanner(System.in);

    public void initClient() {
        try {
            cliente = new Socket("localhost", PUERTO);

            fentrada = new DataInputStream(cliente.getInputStream());
            fsalida = new DataOutputStream(cliente.getOutputStream());

            leerTexto();

            escribirTexto();

            leerTexto();

            int numTipoConsulta = teclado.nextInt();
            fsalida.writeInt(numTipoConsulta);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        System.out.println("Iniciado el cliente");
        ClienteCallCenter c = new ClienteCallCenter();

        try {
            c.initClient();
            c.ejecutar();
        } catch (RuntimeException e) {
            System.err.println("El servidor está cerrado");
        }
    }

    public void ejecutar() {

        boolean sigue = true;

        while (sigue) {
            try {
                escribirTexto();

                System.out.println("Esperando respuesta del servidor...");

                if (!mensajeAEnviar.equalsIgnoreCase("adios")) {

                    leerTexto();

                    System.out.println("¿Qué respondes?");
                } else {
                    sigue = false;
                }

            } catch (IOException e) {
                // error provocado por el cierre del servidor
                System.out.println("Se ha cerrado el servidor");
                sigue = false; // sale del bucle
            }
        }
        try {
            leerTexto();

            cliente.close();// cierre del socket
            System.out.println("Te has desconectado");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }// fin de ejecutar

    private void leerTexto() throws IOException {
        mensajeRecibido = fentrada.readUTF();
        System.out.println("El servidor dice: " + mensajeRecibido);
    }

    private void escribirTexto() throws IOException {
        mensajeAEnviar = teclado.nextLine();
        fsalida.writeUTF(mensajeAEnviar);
    }
}
