package Trimestre1.T03.Ejercicios.JuegoAdivinanza;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class HiloAdivinanza implements Runnable {

    JuegoAdivinanza j;
    Socket cliente;
    String nombre;
    DataInputStream entrada;
    DataOutputStream salida;

    public HiloAdivinanza(JuegoAdivinanza j, Socket cliente) {
        this.j = j;
        this.cliente = cliente;
        try {
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

            salida.writeUTF("Introduce tu nombre");
            this.nombre = entrada.readUTF();

            System.out.println("Cliente " + nombre + " conectado");

        } catch (IOException e) {
            System.err.println("Error de entrada y salida");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!j.isAdivinado()) {
            try {
                String mensaje = j.adivinarNumero(this);
                enviarMensajes(mensaje);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            cliente.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    private void enviarMensajes(String mensaje) {
        ArrayList<Socket> clientesConectados = ServidorAdivinanza.getConectados();

        for (Socket clienteConectado : clientesConectados) {
            try {
                DataOutputStream salida = new DataOutputStream(clienteConectado.getOutputStream());
                salida.writeUTF(mensaje);
            } catch (SocketException se) {
                System.err.println("Servidor cerrado");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
