package Trimestre1.T01.Clase.Castillo;

public class Salon {
    boolean puedeSaludar = false;

    public synchronized void saludar(String hilo) {
        if (hilo.contains("Rey")) {
            System.out.println("Saludos súbditos");
            puedeSaludar = true;
        } else {
            while (!puedeSaludar) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Saludos rey (" + hilo + ")");
        }
        notifyAll();
    }
}
