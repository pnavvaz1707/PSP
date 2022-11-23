package Trimestre1.T03.Ejercicios.JuegoAdivinanza;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteAdivinanza {
    private static final int PUERTO = 5555;
    private Socket cliente;

    private String mensajeRecibido;
    private String mensajeAEnviar;
    DataInputStream entrada; // stream de lectura de mensajes
    DataOutputStream salida; // stream de escritura de mensajes

    private static Scanner teclado = new Scanner(System.in);

    public void initClient() throws IOException {
        cliente = new Socket("localhost", PUERTO);

        salida = new DataOutputStream(cliente.getOutputStream());
        entrada = new DataInputStream(cliente.getInputStream());


        leerTexto();

        escribirTexto();
    }

    public static void main(String[] args) {
        try {
            ClienteAdivinanza c = new ClienteAdivinanza();
            c.initClient();
            c.jugar();
        } catch (IOException e) {
            System.err.println("El servidor está cerrado");
        }
    }

    public void jugar() {
        try {
            do {
                leerTexto();

                if (mensajeRecibido.contains("Espere")) {
                    leerTexto();

                    leerTexto();
                }

                escribirTexto();

                System.out.println("Adivinanza enviada");

                leerTexto();

            } while (true);
        } catch (IOException e) {
            System.err.println("El servidor ha cerrado, ya se ha adivinado el número");
        }
    }


    private void leerTexto() throws IOException {
        mensajeRecibido = entrada.readUTF();
        System.out.println("El servidor dice: " + mensajeRecibido);
    }


    private void escribirTexto() throws IOException {
        mensajeAEnviar = teclado.nextLine();
        salida.writeUTF(mensajeAEnviar);
    }
}
