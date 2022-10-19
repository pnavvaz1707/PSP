package Trimestre1.T02.Ejercicios.Basicos;

public class Ej1Factorial extends Thread {
    private int num;

    Ej1Factorial(int num) {
        this.num = num;
        System.out.println("Creando --> " + this.getName());
    }

    @Override
    public void run() {
        for (int i = num -1; i > 1; i--) {
            num = num * i;
        }
        System.out.println("Factorial --> " + num);
    }
}
