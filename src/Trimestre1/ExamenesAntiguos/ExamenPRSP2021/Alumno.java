package Trimestre1.ExamenesAntiguos.ExamenPRSP2021;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Alumno {
    private final int PUERTO = 5555;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private String mensajeRecibido;
    private String mensajeAEnviar;
    private Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Alumno alumno = new Alumno();
            alumno.iniciarAlumno();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void iniciarAlumno() throws IOException {
        Socket conexion = new Socket("localhost", PUERTO);

        entrada = new DataInputStream(conexion.getInputStream());
        salida = new DataOutputStream(conexion.getOutputStream());

        //Profesor pregunta nombre
        leerTexto();

        //Alumno introduce nombre
        escribirTexto();

        //Profesor realiza la pregunta
        leerTexto();

        //Alumno responde pregunta
        escribirTexto();

        //Profesor agradece
        leerTexto();
    }

    private void leerTexto() throws IOException {
        mensajeRecibido = entrada.readUTF();
        System.out.println("El profesor dice: " + mensajeRecibido);
    }

    private void escribirTexto() throws IOException {
        mensajeAEnviar = teclado.nextLine();
        salida.writeUTF(mensajeAEnviar);
    }
}
