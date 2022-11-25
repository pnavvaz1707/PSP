package Trimestre1.T03.Ejercicios.peval2prsp2223;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Clase que se comporta como un hilo y atiende al cliente una vez conectado al servidor
 */
public class HiloServidorCallCenter implements Runnable {

    /**
     * Objeto tipo Socket que referenciará la conexión del cliente con el servidor del CallCenter
     */
    Socket cliente;

    /**
     * Cadena de texto que contiene el nombre del cliente
     */
    String nombre;

    /**
     * Stream de lectura de mensajes en la conexión realizada
     */
    DataInputStream entrada;

    /**
     * Stream de escritura de mensajes en la conexión realizada
     */
    DataOutputStream salida;

    /**
     * Escáner para leer por teclado los datos que lo requieran
     */
    Scanner teclado = new Scanner(System.in);

    /**
     * Constructor parametrizado de la clase HiloServidorCallCenter
     *
     * @param cliente (Socket que referencia la conexión del cliente con el servidor)
     */
    public HiloServidorCallCenter(Socket cliente) {
        this.cliente = cliente;

        try {
            //Recogemos los torrentes de datos y escritura de la conexión
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
        } catch (IOException e) {
            System.err.println("Error de entrada y salida");
            e.printStackTrace();
        }
    }

    /**
     * Método run de la interfaz Runnable
     */
    @Override
    public void run() {
        try {
            salida.writeUTF("Introduce un nombre: ");
            nombre = entrada.readUTF();
            System.out.println(nombre + " seleccionando tipo de consulta...");
            String mensajeSelConsulta = "Selecciona el tipo de su consulta";

            //Mostramos los posibles tipos de consultas al cliente
            for (int i = 0; i < ServidorCallCenter.tiposConsultas.length; i++) {
                mensajeSelConsulta += "\n" + i + ". " + ServidorCallCenter.tiposConsultas[i];
            }
            salida.writeUTF(mensajeSelConsulta);

            int numTipoConsulta = entrada.readInt();
            System.out.println("El " + nombre + " ha seleccionado " + ServidorCallCenter.tiposConsultas[numTipoConsulta]);

            //Obtenemos el tiempo en el que se ha iniciado la consulta
            int tiempoInicio = (int) System.currentTimeMillis();

            switch (ServidorCallCenter.tiposConsultas[numTipoConsulta]) {
                case "Futurología":
                    try {
                        salida.writeUTF("Estás en la consulta de Futurología, ¿qué deseas?");

                        realizarConsulta();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "Meeting":
                    try {
                        salida.writeUTF("Estás en la consulta de Meeting, ¿qué deseas?");

                        realizarConsulta();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "Compras":
                    try {
                        salida.writeUTF("Estás en la consulta de Compras, ¿qué deseas?");

                        realizarConsulta();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }

            //Calculamos el tiempo total de la consulta en minutos y lo multiplicamos por el precio por minuto para saber el precio total de la consulta
            int tiempoTotal = (int) (System.currentTimeMillis() - tiempoInicio) / 60000;
            float precio = (float) (tiempoTotal * 1.20);
            salida.writeUTF(nombre + " debe pagar " + precio + "€ (Estancia: " + tiempoTotal + " minuto(s))");

            //Desconectamos al cliente y restamos uno a las conexiones actuales del servidor
            System.out.println(nombre + " ha desconectado");
            cliente.close();
            ServidorCallCenter.conexionesActuales--;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método en el que se comunica el servidor con el cliente hasta que el cliente diga "adios"
     *
     * @throws IOException (Excepción que ocurre cuando el cliente se ha desconectado mientras el servidor esperaba o le mandaba un dato)
     */
    private void realizarConsulta() throws IOException {
        boolean sigue = true;
        String mensajeRecibido;
        String mensajeAEnviar;

        entrada.readUTF();

        while (sigue) {
            mensajeRecibido = entrada.readUTF();

            if (!mensajeRecibido.equalsIgnoreCase("adios")) {
                System.out.println(nombre + " dice: " + mensajeRecibido);

                System.out.println("¿Qué respondes a " + nombre + "?");
                mensajeAEnviar = teclado.nextLine();
                salida.writeUTF(mensajeAEnviar);

                System.out.println("Esperando respuesta de " + nombre + "...");
            } else {
                sigue = false;
            }
        }
    }
}
