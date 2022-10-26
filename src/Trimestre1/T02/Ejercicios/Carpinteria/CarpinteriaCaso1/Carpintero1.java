package Trimestre1.T02.Ejercicios.Carpinteria.CarpinteriaCaso1;

import Trimestre1.T02.Ejercicios.peval1psp2223.Colores;

public class Carpintero1 extends Thread {
    Utensilios1 u;
    String color;

    public Carpintero1(String nombre, Utensilios1 u, String color) {
        this.setName(nombre);
        this.u = u;
        this.color = color;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 6; i++) {
                u.cortarPieza(this);
                Thread.sleep(tiempoAccion());
            }
            for (int i = 0; i < 6; i++) {
                ensamblarPiezas();
                Thread.sleep(tiempoAccion());
            }
            for (int i = 0; i < 6; i++) {
                u.apuntillarPieza(this);
                Thread.sleep(tiempoAccion());
            }
            u.lijarSilla(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void ensamblarPiezas() {
        System.out.println(getColor() + "Pieza ensamblada" + Colores.ANSI_RESET);
    }

    public String getColor() {
        return color;
    }

    private int tiempoAccion() {
        return (int) (Math.random() * 3 + 1);
    }
}
