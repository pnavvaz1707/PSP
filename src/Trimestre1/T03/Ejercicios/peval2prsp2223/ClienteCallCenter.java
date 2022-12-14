package Trimestre1.T03.Ejercicios.peval2prsp2223;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Clase que hará de cliente del CallCenter
 */
public class ClienteCallCenter {
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
    DataInputStream fentrada;

    /**
     * Stream de escritura de mensajes en la conexión realizada
     */
    DataOutputStream fsalida;

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
    public void initClient() throws Exception {
        try {
            cliente = new Socket("localhost", PUERTO);

            //Recogemos los torrentes de datos y escritura de la conexión
            fentrada = new DataInputStream(cliente.getInputStream());
            fsalida = new DataOutputStream(cliente.getOutputStream());

            //Conectamos con el servidor, si está lleno, se lanzará la excepción indicando que el servidor está lleno
            try {
                leerTexto();

                escribirTexto();

                leerTexto();
            } catch (SocketException e) {
                throw new Exception("El servidor está lleno");
            }

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
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método en el que se comunica el cliente con el servidor hasta que el cliente diga "adios"
     */
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
                // Error provocado por el cierre del servidor
                System.out.println("Se ha cerrado el servidor");
                sigue = false; // Sale del bucle
            }
        }
        try {
            leerTexto();

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
        mensajeRecibido = fentrada.readUTF();
        System.out.println("El servidor dice: " + mensajeRecibido);
    }

    /**
     * Método que lee una cadena de texto por teclado y se la envía al servidor
     *
     * @throws IOException (Excepción que ocurre cuando el servidor cierra y el cliente trata de enviar datos)
     */
    private void escribirTexto() throws IOException {
        mensajeAEnviar = teclado.nextLine();
        fsalida.writeUTF(mensajeAEnviar);
    }
}
