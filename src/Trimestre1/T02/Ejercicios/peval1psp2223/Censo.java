package Trimestre1.T02.Ejercicios.peval1psp2223;

import Trimestre1.T02.Colores;

import java.util.ArrayList;
import java.util.List;

public class Censo {


    ArrayList<Integer> ids = new ArrayList<>(List.of(1, 2, 3, 4, 6, 8, 10, 11, 13, 14, 15, 18, 19, 20, 22, 23, 24, 28, 29, 30));
    ArrayList<Integer> cola = new ArrayList<>();
    boolean recontando = false;

    int numVotantes = 0;
    int votos = 0;

    public void estaEnElCenso(Votante votante) {
        System.out.println("ID --> " + votante.getDni());
        if (ids.contains(votante.getDni())) {
            Colores.imprimirMorado("El cliente con dni " + votante.getDni() + " ha entrado en la cola del censo (turno " + (getCola().size()) + ")");
            getCola().add(votante.getDni());
            votante.puedeVotar = true;
        } else {
            Colores.imprimirRojo("El cliente con dni " + votante.getDni() + " no está en el censo");
            votante.puedeVotar = false;
        }
    }

    public synchronized void votar(Votante votante) {
        while (!getCola().get(0).equals(votante.getDni()) || recontando) {
            try {
                Colores.imprimirRojo(votante.getDni() + " ha intentado colarse");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Colores.imprimirAzul("El votante de dni " + votante.getDni() + " comienza a votar");
            Thread.sleep(tiempoVoto());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("El votante de dni " + votante.getDni() + " acaba de votar");
        notifyAll();
        votos++;
        getCola().remove(0);
    }

    public void recontarVotos() {
        try {
            Thread.sleep(5000);
            recontando = true;
            System.out.println("Los votos actuales son --> " + votos);
            recontando = false;
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int tiempoVoto() {
        return (int) (Math.random() * 3000 + 1000);
    }

    public synchronized ArrayList<Integer> getCola() {
        return cola;
    }
}
