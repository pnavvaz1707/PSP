package Trimestre1.T02.Ejercicios.Carpinteria.CarpinteriaCaso1;

import Trimestre1.T02.Colores;

public class Utensilios1 {

    boolean sierraUtilizada = false;
    boolean martilloUtilizado = false;
    boolean lijadoraUtilizada = false;

    public synchronized void cortarPieza(Carpintero1 carpintero){
        while (sierraUtilizada){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sierraUtilizada = true;
        imprimirTexto(carpintero.getColor(), carpintero.getName() + " está cortando una pieza");
        sierraUtilizada = false;
    }

    public synchronized void apuntillarPieza(Carpintero1 carpintero){
        while (martilloUtilizado){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        martilloUtilizado = true;
        imprimirTexto(carpintero.getColor(),carpintero.getName() + " está apuntillando las piezas");
        martilloUtilizado = false;
    }

    public synchronized void lijarSilla(Carpintero1 carpintero){
        while (lijadoraUtilizada){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lijadoraUtilizada = true;
        imprimirTexto(carpintero.getColor(), carpintero.getName() + " está lijando la silla");
        lijadoraUtilizada = false;
    }

    private static void imprimirTexto(String color, String msg) {
        System.out.println(color + msg + Colores.ANSI_RESET);
    }
}
