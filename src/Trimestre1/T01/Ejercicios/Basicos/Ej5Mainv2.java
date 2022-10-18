package Trimestre1.T01.Ejercicios.Basicos;

public class Ej5Mainv2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Ej5Sorteov2("Decenas: "));
        Thread t2 = new Thread(new Ej5Sorteov2("Unidades: "));
        Thread t3 = new Thread(new Ej5Sorteov2("Unidades: "));
        Thread t4 = new Thread(new Ej5Sorteov2("Unidades: "));
        Thread t5 = new Thread(new Ej5Sorteov2("Unidades: "));
        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
            t4.start();
            t4.join();
            t5.start();
            t5.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
