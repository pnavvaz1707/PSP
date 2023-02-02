package Trimestre1.T02.Ejercicios.McAuto;

import Trimestre1.T02.Ejercicios.peval1psp2223.Colores;

import java.util.ArrayList;

public class Ventanilla {
    private ArrayList<String> colaRecoger = new ArrayList<>();
    private ArrayList<String> colaPedir = new ArrayList<>();

    public void ponerEnCola(String cliente) {
        colaPedir.add(cliente);
        Colores.imprimirAzul("El " + cliente + " se ha puesto en cola");
    }

    public synchronized void pedir(String cliente) {
        try {
            while (!colaPedir.get(0).equals(cliente)) {
                Colores.imprimirRojo("El " + cliente + " se ha intentado colar para pedir");
                wait();
            }
            System.out.println("El " + cliente + " ha empezado a pedir");
            Thread.sleep(tiempoPedido());
            Colores.imprimirMorado("El " + cliente + " ha terminado de comprar");
            colaPedir.remove(0);
            colaRecoger.add(cliente);
            notifyAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void pagar(String cliente) {
        try {
            while (!colaRecoger.get(0).equals(cliente)) {
                Colores.imprimirRojo("El " + cliente + " se ha intentado colar para recoger");
                wait();
            }
            System.out.println("El " + cliente + " ha recogido el pedido");
            colaRecoger.remove(0);
//            Colores.imprimirAzul("Cola actual");
//            for (int i = 0; i < colaRecoger.size(); i++) {
//                Colores.imprimirMorado(i + ". " + colaRecoger.get(i));
//            }
            notifyAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private int tiempoPedido() {
        return (int) ((Math.random() * 5000) + 1000);
    }

    private int tiempoPago() {
        return (int) ((Math.random() * 2000) + 1000);
    }
}
