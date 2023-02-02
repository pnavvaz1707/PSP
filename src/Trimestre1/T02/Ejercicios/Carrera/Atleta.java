package Trimestre1.T02.Ejercicios.Carrera;

public class Atleta extends Thread {

    Pista p;
    int distancaRecorrida = 0;

    public Atleta(String name, Pista p) {
        this.setName(name);
        this.p = p;
    }

    @Override
    public void run() {
        p.prepararse();
        while (distancaRecorrida < p.getDistanciaPista()) {
            try {
                int tiempoZancada = tiempoZancada();
                sleep(tiempoZancada);
                distancaRecorrida++;
                System.out.println(this.getName() + " zancada dada en " + tiempoZancada + " ms | Distancia actual (" + distancaRecorrida + ")");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        p.notificarDorsal(this);
    }

    public int tiempoZancada() {
        return (int) ((Math.random() * 30) + 90);
    }
}
