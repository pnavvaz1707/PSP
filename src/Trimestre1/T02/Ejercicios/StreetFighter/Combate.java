package Trimestre1.T02.Ejercicios.StreetFighter;

public class Combate extends Thread {
    Ring r;

    public Combate(Ring r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (!r.isTerminado()) {
            r.decidirTurno();
        }
    }


}
