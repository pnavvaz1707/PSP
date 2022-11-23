package Trimestre1.T03.Ejercicios.peval2prsp2223;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorCallCenter {
    private ServerSocket servidor;
    private final int PUERTO = 5555;
    static int conexionesActuales = 0;
    private final int MAXIMO_CONEXIONES = 3;

    private DataOutputStream salida;
    private DataInputStream entrada;

    private ArrayList<Socket> conectados = new ArrayList<>();

    private static final String[] tiposConsultas = {
            "Futurología",
            "Meeting",
            "Compras"
    };

    private void initServer() {
        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Se ha iniciado el servidor");
            while (conexionesActuales < MAXIMO_CONEXIONES) {
                Socket s;

                s = servidor.accept();

                entrada = new DataInputStream(s.getInputStream());
                salida = new DataOutputStream(s.getOutputStream());

                salida.writeUTF("Introduce un nombre: ");
                String nombre = entrada.readUTF();

                System.out.println(nombre + " seleccionando tipo de consulta...");
                String mensajeSelConsulta = "Selecciona el tipo de su consulta";

                for (int i = 0; i < tiposConsultas.length; i++) {
                    mensajeSelConsulta += "\n" + i + ". " + tiposConsultas[i];
                }
                salida.writeUTF(mensajeSelConsulta);

                int numTipoConsulta = entrada.readInt();
                System.out.println("El " + nombre + " ha seleccionado " + tiposConsultas[numTipoConsulta]);

                conectados.add(s);

                conexionesActuales++;

                Thread hilo = new Thread(new HiloServidorCallCenter(s, tiposConsultas[numTipoConsulta], nombre));
                hilo.start();
            }
            if (!servidor.isClosed()) {
                try {
                    System.err.println("El servidor se ha llenado y ya no acepta más clientes");
                    servidor.close();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ServidorCallCenter servidorAdivinanza = new ServidorCallCenter();
        servidorAdivinanza.initServer();
    }

    public int getConexionesActuales() {
        return conexionesActuales;
    }

    public ArrayList<Socket> getConectados() {
        return conectados;
    }

    public void setConectados(ArrayList<Socket> conectados) {
        this.conectados = conectados;
    }
}