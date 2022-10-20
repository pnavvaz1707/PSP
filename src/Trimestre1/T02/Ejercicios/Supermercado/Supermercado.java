package Trimestre1.T02.Ejercicios.Supermercado;

import Trimestre1.T02.Colores;

import java.util.ArrayList;

public class Supermercado {
    private ArrayList<String> cola = new ArrayList<>();

    public void comprar(String cliente) {
        try {
            System.out.println("El " + cliente + " ha empezado a comprar");
            Thread.sleep(tiempoCompra());
            Colores.imprimirMorado("El " + cliente + " ha terminado de comprar");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void ponerEnCola(String cliente) {
        cola.add(cliente);
        Colores.imprimirAzul("El " + cliente + " se ha puesto en cola");
    }

    public synchronized void pagar(String cliente) {
        while (!cola.get(0).equals(cliente)){
            try {
                Colores.imprimirRojo("El " + cliente + " se ha intentado colar");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            System.out.println("El " + cliente + " comienza a pagar");
            Thread.sleep(tiempoPago());
            Colores.imprimirVerde("El " + cliente + " ya ha pagado");
            cola.remove(0);
            Colores.imprimirAzul("Cola actual");
            for (int i = 0; i < cola.size(); i++) {
                Colores.imprimirMorado(i + ". " + cola.get(i));
            }
            notifyAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private int tiempoCompra() {
        return (int) ((Math.random() * 5000) + 1000);
    }

    private int tiempoPago() {
        return (int) ((Math.random() * 2000) + 1000);
    }
}
