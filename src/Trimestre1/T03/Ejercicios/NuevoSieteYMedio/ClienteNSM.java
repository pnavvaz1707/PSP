package Trimestre1.T03.Ejercicios.NuevoSieteYMedio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteNSM {

    private final int PUERTO = 5555;

    private DataInputStream entrada;
    private DataOutputStream salida;

    private String mensajeRecibido;
    private String mensajeAEnviar;

    private Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            ClienteNSM cliente = new ClienteNSM();
            cliente.iniciarCliente();
            cliente.ejecutar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void iniciarCliente() throws IOException {
        Socket conexion = new Socket("localhost", PUERTO);

        entrada = new DataInputStream(conexion.getInputStream());
        salida = new DataOutputStream(conexion.getOutputStream());

        System.out.println("Cliente iniciado");

        mensajeRecibido = entrada.readUTF();
        System.out.println("El servidor dice: " + mensajeRecibido);

        mensajeAEnviar = teclado.nextLine();
        salida.writeUTF(mensajeAEnviar);

    }

    private void ejecutar() throws IOException {
        System.out.println("Empieza");
        while (true){
            mensajeRecibido = entrada.readUTF();
            System.out.println("El servidor dice: " + mensajeRecibido);

            mensajeAEnviar = teclado.nextLine();
            salida.writeUTF(mensajeAEnviar);
        }
    }
}
