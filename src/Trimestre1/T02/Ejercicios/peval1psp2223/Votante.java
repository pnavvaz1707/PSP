package Trimestre1.T02.Ejercicios.peval1psp2223;

import Trimestre1.T02.Colores;

public class Votante extends Thread{

    private int dni = 1;
    ColegioElectoral c;

    boolean puedeVotar;

    public Votante(ColegioElectoral c, int dni){
        this.dni = dni;
        this.c = c;
    }

    @Override
    public void run() {
        Colores.imprimirMorado("El votante con dni " + this.getDni() + " ha entrado al censo");
        c.estaEnElCenso(this);
        if (puedeVotar){
            c.votar(this);
        }
        Colores.imprimirMorado("El votante con dni " + this.getDni() + " ha salido del censo");
    }

    public int getDni() {
        return dni;
    }
}
