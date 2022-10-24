package Trimestre1.T02.Ejercicios.peval1psp2223;

import Trimestre1.T02.Colores;

import java.util.ArrayList;
import java.util.List;

public class ColegioElectoral {

    ArrayList<Integer> ids = new ArrayList<>(List.of(1, 2, 3, 4, 6, 8, 10, 11, 13, 14, 15, 18, 19, 20, 22, 23, 24, 28, 29, 30));
    ArrayList<Integer> cola = new ArrayList<>();
    boolean recontando = false;
    int numVotos = 0;

    public void estaEnElCenso(Votante votante) {
        Colores.imprimirAzul("El votante con dni " + votante.getDni() + " empieza a mirar si esta en el censo");
        if (ids.contains(votante.getDni())) {
            getCola().add(votante.getDni());
            Colores.imprimirVerde("El votante con dni " + votante.getDni() + " ha entrado en la cola del censo turno (" + getCola().size() + ")");
            votante.puedeVotar = true;
        } else {
            Colores.imprimirRojo("El votante con dni " + votante.getDni() + " no está en el censo");
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
        numVotos++;
        getCola().remove(0);
        notifyAll();
    }

    public void recontarVotos() {
        boolean sigue = true;
        int numVotosAnterior = 0;
        while (sigue) {
            try {
                if (numVotos == ids.size()) {
                    sigue = false;
                } else {
                    Thread.sleep(5000);
                    recontando = true;
                    System.out.println("Los votos de este recuento son --> " + (numVotos - numVotosAnterior));
                    numVotosAnterior = numVotos;
                    recontando = false;
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Los recuentos totales de la votación son --> " + numVotos);
    }

    public static int tiempoVoto() {
        return (int) (Math.random() * 3000 + 1000);
    }

    public synchronized ArrayList<Integer> getCola() {
        return cola;
    }
}
