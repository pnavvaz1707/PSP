package Trimestre1.T03.Ejercicios.peval2prsp2223;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class HiloServidorCallCenter implements Runnable {

    Socket cliente;
    String tipoConsulta;
    String nombre;

    DataInputStream entrada;
    DataOutputStream salida;

    Scanner teclado = new Scanner(System.in);

    public HiloServidorCallCenter(Socket cliente, String tipoConsulta, String nombre) {
        this.cliente = cliente;
        this.tipoConsulta = tipoConsulta;
        this.nombre = nombre;

        try {
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
        } catch (IOException e) {
            System.err.println("Error de entrada y salida");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int tiempoInicio = (int) System.currentTimeMillis();

        switch (tipoConsulta) {
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

        try {
            int tiempoTotal = (int) (System.currentTimeMillis() - tiempoInicio) / 60000;
            float precio = (float) (tiempoTotal * 1.20);
            salida.writeUTF(nombre + " debe pagar " + precio + "€ (Estancia: " + tiempoTotal + " minuto(s))");

            System.out.println(nombre + " ha desconectado");
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void realizarConsulta() throws IOException {
        boolean sigue = true;
        String mensajeRecibido;
        String mensajeAEnviar;

        entrada.readUTF();

        while (sigue) {
            mensajeRecibido = entrada.readUTF();

            if (!mensajeRecibido.equalsIgnoreCase("adios")) {
                System.out.println(nombre + " dice: " + mensajeRecibido);

                System.out.println("¿Qué respondes?");
                mensajeAEnviar = teclado.nextLine();
                salida.writeUTF(mensajeAEnviar);

                System.out.println("Esperando respuesta de " + nombre + "...");
            } else {
                sigue = false;
            }
        }
    }
}
