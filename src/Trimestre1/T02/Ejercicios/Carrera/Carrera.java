package Trimestre1.T02.Ejercicios.Carrera;

public class Carrera extends Thread {

    Pista p;

    public Carrera(Pista p) {
        this.p = p;
        this.setName("Carrera");
    }

    @Override
    public void run() {
        p.indicarPistoletazo();
        p.resultadosCarrera();
    }
}
