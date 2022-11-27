package Trimestre1.ExamenesAntiguos.ExamenPRSP2021;

import java.io.IOException;
import java.util.ArrayList;

public class Conexiones {
    ArrayList<HiloProfesor> alumnosConectados;

    public Conexiones() {
        this.alumnosConectados = new ArrayList<>();
    }

    public synchronized void preguntar(HiloProfesor hiloProfesor) throws IOException {
        hiloProfesor.getSalida().writeUTF("Pregunta");
    }

    public synchronized void recibirRespuesta(HiloProfesor hiloProfesor) throws IOException {
        System.out.println(hiloProfesor.getEntrada().readUTF());
        hiloProfesor.getSalida().writeUTF("Gracias");
        alumnosConectados.remove(hiloProfesor);
        System.out.println("Quedan " + alumnosConectados.size() + " alumnos");
    }

    public void registrar(HiloProfesor hiloProfesor) throws IOException {
        hiloProfesor.getSalida().writeUTF("Introduce tu nombre");
        hiloProfesor.setNombreAlumno(hiloProfesor.getEntrada().readUTF());

        alumnosConectados.add(hiloProfesor);
        System.out.println("Se ha conectado " + hiloProfesor.getNombreAlumno());
    }
}
