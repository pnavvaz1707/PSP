package Trimestre1.ExamenesAntiguos.ExamenPRSP2021;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Conexiones {
    private final ArrayList<HiloProfesor> alumnosConectados;

    private int maximoAlumnos;
    public static HashMap<HiloProfesor, String> mensajes = new HashMap<>();

    public Conexiones(int maximoAlumnos) {
        this.maximoAlumnos = maximoAlumnos;
        this.alumnosConectados = new ArrayList<>();
    }

    public void registrar(HiloProfesor hiloProfesor) throws IOException {
        hiloProfesor.getSalida().writeUTF("Introduce tu nombre");
        hiloProfesor.setNombreAlumno(hiloProfesor.getEntrada().readUTF());

        alumnosConectados.add(hiloProfesor);
        System.out.println("Se ha conectado " + hiloProfesor.getNombreAlumno());
    }

    public synchronized void preguntar(HiloProfesor hiloProfesor) throws IOException {
        notifyAll();
        while (alumnosConectados.size() < maximoAlumnos) {
            try {
//                System.err.println("Somos " + alumnosConectados.size());
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        hiloProfesor.getSalida().writeUTF("Pregunta");
    }

    public void recibirRespuesta(HiloProfesor hiloProfesor) throws IOException {
        String respuesta = hiloProfesor.getEntrada().readUTF();
        System.out.println("El alumno " + hiloProfesor.getNombreAlumno() + " ha respondido: " + respuesta);
        mensajes.put(hiloProfesor, respuesta);

        hiloProfesor.getSalida().writeUTF("Gracias");
        alumnosConectados.remove(hiloProfesor);
        System.out.println("Quedan " + alumnosConectados.size() + " alumnos por responder");
    }

    public synchronized void verResultados(HiloProfesor hiloProfesor) throws IOException {
        notifyAll();
        while (alumnosConectados.size() >= 1) {
            try {
//                System.err.println("Espera q contesten todos");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        StringBuilder resultados = new StringBuilder("Resultados: \n");
        for (Map.Entry<HiloProfesor, String> respuesta : mensajes.entrySet()) {
            if (respuesta.getKey() != hiloProfesor) {
                resultados.append("  --> ").append(respuesta.getKey().getNombreAlumno()).append(" ha respondido ").append(respuesta.getValue()).append("\n");
            }
        }
        hiloProfesor.getSalida().writeUTF(String.valueOf(resultados));
    }
}
