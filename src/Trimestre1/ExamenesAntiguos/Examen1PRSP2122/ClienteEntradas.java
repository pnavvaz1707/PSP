package Trimestre1.ExamenesAntiguos.Examen1PRSP2122;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEntradas {
    private final int PUERTO = 5555;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private Scanner teclado = new Scanner(System.in);

    private void iniciarCliente() throws IOException {
        Socket s = new Socket("localhost",PUERTO);
        entrada = new DataInputStream(s.getInputStream());
        salida = new DataOutputStream(s.getOutputStream());

        System.out.println(entrada.readUTF());
        salida.writeUTF(teclado.nextLine());

        System.out.println(entrada.readUTF());
        salida.writeInt(teclado.nextInt());

        System.out.println(entrada.readUTF());
    }

    public static void main(String[] args) {
        try {
            ClienteEntradas clienteEntradas = new ClienteEntradas();
            clienteEntradas.iniciarCliente();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
