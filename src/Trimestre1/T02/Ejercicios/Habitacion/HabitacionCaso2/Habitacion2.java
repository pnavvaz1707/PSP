package Trimestre1.T02.Ejercicios.Habitacion.HabitacionCaso2;

import Trimestre1.T02.Ejercicios.peval1psp2223.Colores;

public class Habitacion2 {

    Puerta2 p;

    public Habitacion2() {
        p = new Puerta2();
    }

    public synchronized void abrir(String persona) {
        while (p.isAbierta()) {
            try {
                Colores.imprimirRojo(persona + " ha intentado abrir la puerta");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        p.setAbierta(true);
        Colores.imprimirMorado(persona + " ha abierto la puerta");
        notifyAll();

    }

    public synchronized void cerrar(String persona) {
        while (!p.isAbierta()) {
            try {
                Colores.imprimirRojo(persona + " ha intentado cerrar la puerta");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        p.setAbierta(false);
        Colores.imprimirAzul(persona + " ha cerrado la puerta");
        notifyAll();
    }

}
