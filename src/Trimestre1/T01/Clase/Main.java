package Trimestre1.T01.Clase;

public class Main {
    public static void main(String[] args) {
        Tuberia t = new Tuberia();
        Hilo h1 = new Hilo(t);
        Hilo h2 = new Hilo(t);
        h1.start();
        h2.start();
    }
}
