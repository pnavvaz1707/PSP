package Trimestre1.T02.Ejercicios.Basicos;

public class Ej5Sorteo extends Thread {

    private String nombre;

    public Ej5Sorteo(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.println(nombre + (int) (Math.random() * 9));
    }
}
