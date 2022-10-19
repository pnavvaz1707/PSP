package Trimestre1.T02.Ejercicios.Basicos;

public class Ej5Sorteov2 implements Runnable{

    private String nombre;

    public Ej5Sorteov2 (String nombre){
        this.nombre = nombre;
    }
    @Override
    public void run() {
        System.out.println(nombre + (int) (Math.random() * 9));
    }
}
