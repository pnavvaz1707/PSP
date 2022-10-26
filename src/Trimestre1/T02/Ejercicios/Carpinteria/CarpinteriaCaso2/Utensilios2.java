package Trimestre1.T02.Ejercicios.Carpinteria.CarpinteriaCaso2;

import Trimestre1.T02.Ejercicios.peval1psp2223.Colores;

public class Utensilios2 {

    String ultimaPersonaSierra = "";
    String ultimaPersonaMartillo = "";
    String ultimaPersonaLijadora = "";
    boolean sierraUtilizada = false;
    boolean martilloUtilizado = false;
    boolean lijadoraUtilizada = false;

    public synchronized void cortarPieza(Carpintero2 carpintero){
        while (sierraUtilizada || ultimaPersonaSierra.equals(carpintero.getName())){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sierraUtilizada = true;
        ultimaPersonaSierra = carpintero.getName();
        imprimirTexto(carpintero.getColor(), carpintero.getName() + " está cortando una pieza");
        sierraUtilizada = false;
        notifyAll();
    }

    public synchronized void apuntillarPieza(Carpintero2 carpintero){
        while (martilloUtilizado || ultimaPersonaMartillo.equals(carpintero.getName())){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        martilloUtilizado = true;
        ultimaPersonaMartillo = carpintero.getName();
        imprimirTexto(carpintero.getColor(),carpintero.getName() + " está apuntillando las piezas");
        martilloUtilizado = false;
        notifyAll();
    }

    public synchronized void lijarSilla(Carpintero2 carpintero){
        while (lijadoraUtilizada || ultimaPersonaLijadora.equals(carpintero.getName())){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lijadoraUtilizada = true;
        ultimaPersonaLijadora = carpintero.getName();
        imprimirTexto(carpintero.getColor(), carpintero.getName() + " está lijando la silla");
        lijadoraUtilizada = false;
        notifyAll();
    }

    private static void imprimirTexto(String color, String msg) {
        System.out.println(color + msg + Colores.ANSI_RESET);
    }
}
