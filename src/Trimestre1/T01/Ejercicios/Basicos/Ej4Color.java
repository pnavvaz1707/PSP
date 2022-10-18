package Trimestre1.T01.Ejercicios.Basicos;

import java.util.Scanner;

public class Ej4Color extends Thread {

    Ej4Color() {
        System.out.println("Hilo creado --> " + this.getName());
    }

    @Override
    public void run() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("¿De qué color es este hilo? (" + this.getName() + ")");
        String color = teclado.next();
        System.out.println("Hola, el mundo es de color " + color + " (" + this.getName() + ")");
    }
}
