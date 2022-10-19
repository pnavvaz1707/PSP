package Trimestre1.T02.Ejercicios.Basicos;

public class Ej3Contar500 extends Thread {

    Ej3Contar500() {
        System.out.println("Hilo creado --> " + this.getName());
    }

    @Override
    public void run() {
        for (int i = 0; i <= 499; i++) {
            System.out.println(i + " (" + this.getName() + ")");
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }
}
