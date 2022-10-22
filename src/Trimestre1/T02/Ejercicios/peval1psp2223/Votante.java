package Trimestre1.T02.Ejercicios.peval1psp2223;

public class Votante extends Thread{

    private int dni = 1;
    Censo c;

    boolean puedeVotar;

    public Votante(Censo c, int dni){
        this.dni = dni;
        this.c = c;
        this.c.numVotantes++;
    }

    @Override
    public void run() {
        c.estaEnElCenso(this);
        if (puedeVotar){
            c.votar(this);
        }
    }

    public int getDni() {
        return dni;
    }
}
