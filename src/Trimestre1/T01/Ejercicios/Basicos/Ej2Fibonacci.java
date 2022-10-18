package Trimestre1.T01.Ejercicios.Basicos;

public class Ej2Fibonacci extends Thread{

    private int num;

    Ej2Fibonacci (int num) {
        System.out.println("Hilo creado");
        this.num = num;
    }

    @Override
    public void run() {
        int numAnterior = 0;
        int numActual = 1;
        System.out.println(1);
        for (int i = 1; i < num; i++) {
            System.out.println(numActual + numAnterior);
            numActual = numActual + numAnterior;
            numAnterior = numActual - numAnterior;
        }
    }
}
