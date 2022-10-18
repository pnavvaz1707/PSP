package Trimestre1.T01.Ejercicios.Basicos;

public class Ej0Consumidor extends Thread {

    Ej0Tuberia t;

    public Ej0Consumidor(Ej0Tuberia t) {
        this.t = t;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            t.recoger();
        }
    }
}
