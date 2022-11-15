package Trimestre1.T03.Clase.JuegoAdivinanza;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class HiloAdivinanza implements Runnable {

    JuegoAdivinanza j;
    Socket cliente;
    DataInputStream entrada;
    ServidorAdivinanza servidorAdivinanza;

    public HiloAdivinanza(JuegoAdivinanza j, Socket cliente, ServidorAdivinanza servidorAdivinanza) {
        this.j = j;
        this.cliente = cliente;
        this.servidorAdivinanza = servidorAdivinanza;
        try {
            entrada = new DataInputStream(cliente.getInputStream());
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
    }

    private void enviarMensajes(String mensaje) {
        int i;

        for (i = 0; i < servidorAdivinanza.getConectados().length; i++) {
            Socket s1 = servidorAdivinanza.getConectados()[i];
            try {
                DataOutputStream fsalida = new DataOutputStream(s1.getOutputStream());
                fsalida.writeUTF(mensaje);
            } catch (SocketException se) {

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}