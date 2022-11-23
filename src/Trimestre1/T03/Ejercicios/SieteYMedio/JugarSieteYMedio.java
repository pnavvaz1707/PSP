package Trimestre1.T03.Ejercicios.SieteYMedio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class JugarSieteYMedio {
    /**
     * Puerto donde se alojará el servidor del CallCenter
     */
    private static final int PUERTO = 5555;

    /**
     * Objeto tipo Socket que referenciará la conexión del cliente con el servidor del CallCenter
     */
    private Socket cliente;

    /**
     * Stream de lectura de mensajes en la conexión realizada
     */
    DataInputStream entrada;

    /**
     * Stream de escritura de mensajes en la conexión realizada
     */
    DataOutputStream salida;

    /**
     * Cadena de texto que contiene un mensaje recibido por el servidor
     */
    String mensajeRecibido;

    /**
     * Cadena de texto que contiene un mensaje a enviar al servidor
     */
    String mensajeAEnviar;

    /**
     * Escáner para leer por teclado los datos que lo requieran
     */
    private static Scanner teclado = new Scanner(System.in);

    /**
     * Método que inicia la conexión del cliente con el servidor
     */
    public void iniciarJugador() {
        try {
            cliente = new Socket("localhost", PUERTO);

            //Recogemos los torrentes de datos y escritura de la conexión
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

            leerTexto();

            escribirTexto();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        System.out.println("Iniciado el cliente");
        JugarSieteYMedio j = new JugarSieteYMedio();

        try {
            j.iniciarJugador();
            j.jugar();
        } catch (RuntimeException e) {
            System.err.println("El servidor está cerrado");
        }
    }


    public void jugar() {

        boolean sigue = true;

        while (sigue) {
            try {
                leerTexto();

                //Escribimos lo que queremos hacer
                escribirTexto();

                System.out.println("Esperando respuesta del servidor...");

                //Leemos nuestras cartas
                leerTexto();

                //Leemos el resto
                leerTexto();

                if (mensajeRecibido.contains("terminado")) {
                    sigue = false;
                }

            } catch (IOException e) {
                // Error provocado por el cierre del servidor
                System.out.println("Se ha cerrado el servidor");
                sigue = false; // Sale del bucle
            }
        }
        try {

            cliente.close();// Cierre del socket
            System.out.println("Te has desconectado");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }// fin de ejecutar

    /**
     * Método que lee una cadena de texto enviada por el servidor y la imprime
     *
     * @throws IOException (Excepción que ocurre cuando el servidor cierra y el cliente trata de recibir datos)
     */
    private void leerTexto() throws IOException {
        mensajeRecibido = entrada.readUTF();
        System.out.println("El servidor dice: " + mensajeRecibido);
    }

    /**
     * Método que lee una cadena de texto por teclado y se la envía al servidor
     *
     * @throws IOException (Excepción que ocurre cuando el servidor cierra y el cliente trata de enviar datos)
     */
    private void escribirTexto() throws IOException {
        mensajeAEnviar = teclado.nextLine();
        salida.writeUTF(mensajeAEnviar);
    }
}
