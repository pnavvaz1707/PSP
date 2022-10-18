package Trimestre1.T01.Clase;

public class Hilo extends Thread {

    Tuberia t;

    public Hilo(Tuberia t) {
        this.t = t;
        System.out.println("Hilo creado --> " + this.getName());
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                t.imprimir(this.getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
