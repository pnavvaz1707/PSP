package Trimestre1.T02.Ejercicios.Carrera;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Pista {

    boolean pistoletazo = false;
    int distanciaPista = 5;

    boolean terminada = false;

    int numAtletas = 0;

    ArrayList<String> resultados = new ArrayList<>();

    public synchronized void indicarPistoletazo() {
        while (numAtletas < 8) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            System.out.println("Preparados");
            sleep(1000);
            System.out.println("Listos");
            sleep(1000);
            System.out.println("Ya!");
            sleep(1000);
            System.out.println("Ha dado el pistoletazo de salida");
            pistoletazo = true;
            notifyAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void resultadosCarrera() {
        while (!terminada) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        resultados.forEach(System.out::println);
    }

    public synchronized void prepararse() {
        numAtletas++;
        notifyAll();
        while (!isPistoletazo()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void notificarDorsal(Atleta a) {
        resultados.add(a.getName() + " tarda " + System.currentTimeMillis() + " ms");
        System.out.println("Ha terminado el " + a.getName());
        if (resultados.size() == numAtletas) {
            terminada = true;
            notifyAll();
        }
    }

    public synchronized boolean isPistoletazo() {
        return pistoletazo;
    }

    public int getDistanciaPista() {
        return distanciaPista;
    }
}
