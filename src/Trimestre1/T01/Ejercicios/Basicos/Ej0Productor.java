package Trimestre1.T01.Ejercicios.Basicos;

public class Ej0Productor extends Thread {
    String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Ej0Tuberia t;

    public Ej0Productor(Ej0Tuberia t) {
        this.t = t;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            t.lanzar(letras.charAt((int) (Math.random() * letras.length())));
            System.out.println("Carácter lanzado");
        }
    }
}
