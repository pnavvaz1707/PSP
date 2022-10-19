package Trimestre1.T02.Ejercicios.Basicos;

/*
 * Ejecutar ej
 * Quitar sleep y probar
 * Ejecutor lanza 15 numeros
 * Consumidor recoge 15 nums
 * Ejecutar sin sleep*/


public class Ej0Main {
    public static void main(String[] args)
    {
        Ej0Tuberia t = new Ej0Tuberia();
        Ej0Consumidor consumidor = new Ej0Consumidor(t);
        Ej0Productor productor = new Ej0Productor(t);

        productor.start();
        consumidor.start();
    }
}
