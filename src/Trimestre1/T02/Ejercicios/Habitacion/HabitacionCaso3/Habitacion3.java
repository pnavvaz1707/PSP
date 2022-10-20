package Trimestre1.T02.Ejercicios.Habitacion.HabitacionCaso3;

import Trimestre1.T02.Colores;

public class Habitacion3 {

    Puerta3 p;

    public Habitacion3() {
        p = new Puerta3();
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
