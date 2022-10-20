package Trimestre1.T02.Ejercicios.Habitacion.HabitacionCaso1;

import Trimestre1.T02.Colores;

public class Habitacion1 {

    Puerta1 p;

    public Habitacion1() {
        p = new Puerta1();
    }

    public void abrir(String persona) {
        if (persona.equals("Alice")) {
            synchronized (p) {
                while (p.isAbierta()) {
                    try {
                        p.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                p.setAbierta(true);
                Colores.imprimirMorado(persona + " ha abierto la puerta");
                p.notifyAll();
            }
        }
    }

    public void cerrar(String persona) {
        if (persona.equals("Bob")) {
            synchronized (p) {
                while (!p.isAbierta()) {
                    try {
                        p.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                p.setAbierta(false);
                Colores.imprimirAzul(persona + " ha cerrado la puerta");
                p.notifyAll();
            }
        }
    }
}
