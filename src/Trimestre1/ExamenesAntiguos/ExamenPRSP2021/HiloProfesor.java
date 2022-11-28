package Trimestre1.ExamenesAntiguos.ExamenPRSP2021;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloProfesor implements Runnable {

    private Conexiones conexiones;
    private Socket conexion;
    private String nombreAlumno;
    private DataInputStream entrada;
    private DataOutputStream salida;

    public HiloProfesor(Socket conexion, Conexiones conexiones) throws IOException {
        this.conexiones = conexiones;
        this.conexion = conexion;

        entrada = new DataInputStream(conexion.getInputStream());
        salida = new DataOutputStream(conexion.getOutputStream());
    }

    @Override
    public void run() {

        try {
            conexiones.registrar(this);
            conexiones.preguntar(this);
            conexiones.recibirRespuesta(this);
            conexiones.verResultados(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Conexiones getConexiones() {
        return conexiones;
    }

    public void setConexiones(Conexiones conexiones) {
        this.conexiones = conexiones;
    }

    public Socket getConexion() {
        return conexion;
    }

    public void setConexion(Socket conexion) {
        this.conexion = conexion;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public DataInputStream getEntrada() {
        return entrada;
    }

    public void setEntrada(DataInputStream entrada) {
        this.entrada = entrada;
    }

    public DataOutputStream getSalida() {
        return salida;
    }

    public void setSalida(DataOutputStream salida) {
        this.salida = salida;
    }
}
