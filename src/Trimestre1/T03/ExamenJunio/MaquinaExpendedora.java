package Trimestre1.T03.ExamenJunio;

import Trimestre1.T02.Ejercicios.peval1psp2223.Colores;

public class MaquinaExpendedora {
    int capacidad;

    public MaquinaExpendedora(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void rellenarBotella(Botella b) {
        while (getCapacidad() < b.getCapacidad()) {
            try {
                Colores.imprimirRojo("No hay agua suficiente (" + getCapacidad() + " cl) para la botella de " + b.getCapacidad() + " cl");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        setCapacidad(getCapacidad() - b.getCapacidad());
        Colores.imprimirVerde("La botella de " + b.getCapacidad() + " se ha rellenado (" + getCapacidad() + " cl)");
    }

    public synchronized int getCapacidad() {
        return capacidad;
    }

    public synchronized void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}
