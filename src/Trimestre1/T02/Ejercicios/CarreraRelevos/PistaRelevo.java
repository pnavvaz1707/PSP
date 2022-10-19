package Trimestre1.T02.Ejercicios.CarreraRelevos;

public class PistaRelevo {

    int distanciaPista = 100;

    public synchronized void correr(AtletaRelevo a) {
        while (a.getDistanciaRecorrida() < getDistanciaPista()) {
            try {
                int tiempoZancada = tiempoZancada();
                Thread.sleep(tiempoZancada);
                a.setDistanciaRecorrida(a.getDistanciaRecorrida() + 1);
                System.out.println(a.getName() + " zancada(" + a.getDistanciaRecorrida() + ") dada en " + tiempoZancada + "s");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public int tiempoZancada() {
        return (int) ((Math.random() * 30) + 90);
    }

    public int getDistanciaPista() {
        return distanciaPista;
    }
}
