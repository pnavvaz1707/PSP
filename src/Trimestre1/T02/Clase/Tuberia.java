package Trimestre1.T02.Clase;

public class Tuberia {

    int n = 0;
    String ultimoHilo = "Thread-0";

    public Tuberia() {
        System.out.println("Tubería creada");
    }

    public synchronized void imprimir(String hilo) throws InterruptedException {
        if (ultimoHilo.equals(hilo)) {
            wait();
        }
        n++;
        System.out.println("Está imprimiendo el hilo --> " + hilo + " (" + n + ")");
        ultimoHilo = hilo;
        notifyAll();
    }
}
