package Trimestre1.ExamenesAntiguos.Examen1PRSP2122;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloTaquilla implements Runnable {

    Socket conexion;
    DataOutputStream salida;
    DataInputStream entrada;
    String nombre;

    public HiloTaquilla(Socket conexion) throws IOException {
        this.conexion = conexion;
        this.salida = new DataOutputStream(conexion.getOutputStream());
        this.entrada = new DataInputStream(conexion.getInputStream());
    }

    @Override
    public void run() {
        try {
            salida.writeUTF("Introduce nombre");
            this.nombre = entrada.readUTF();
            salida.writeUTF("¿Cuantas entradas?");
            int numEntradas = entrada.readInt();
            salida.writeUTF("Hola " + nombre + " has comprado " + numEntradas + " entradas");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
