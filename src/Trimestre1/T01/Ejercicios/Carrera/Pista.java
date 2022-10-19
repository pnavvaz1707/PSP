package Trimestre1.T01.Ejercicios.Carrera;

import java.util.ArrayList;

public class Pista {

    boolean pistoletazo = false;
    int distanciaPista = 3;

    boolean terminada = false;

    int numAtletas = 0;

    ArrayList<String> resultados = new ArrayList<>();

    public synchronized void indicarPistoletazo() {
        System.out.println("Ha dado el pistoletazo de salida");
        pistoletazo = true;
        notifyAll();
    }

    public synchronized void resultadosCarrera() {
        while (!terminada){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        resultados.forEach(System.out::println);
    }

    public synchronized void correr(Atleta a) {
        while (!isPistoletazo()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            int tiempoZancada = tiempoZancada();
            Thread.sleep(tiempoZancada);
            System.out.println(a.getName() + " zancada dada en " + tiempoZancada + "s");
            a.setTiempoEmpleado(tiempoZancada);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public synchronized void notificarDorsal(Atleta a) {
        resultados.add(a.getName() + " tarda " + a.getTiempoEmpleado());
        if (resultados.size() == numAtletas) {
            terminada = true;
            notifyAll();
        }
    }

    public int tiempoZancada() {
        return (int) ((Math.random() * 30) + 90);
    }

    public synchronized boolean isPistoletazo() {
        return pistoletazo;
    }

    public int getDistanciaPista() {
        return distanciaPista;
    }

    public void sumarAtleta() {
        this.numAtletas++;
    }
}
