package Trimestre1.T01.Ejercicios.Carrera;

public class Carrera extends Thread {

    Pista p;

    public Carrera(Pista p) {
        this.p = p;
        this.setName("Carrera");
    }

    @Override
    public void run() {
        try {
            System.out.println("Preparados");
            sleep(1000);
            System.out.println("Listos");
            sleep(1000);
            System.out.println("Ya!");
            sleep(1000);
            p.indicarPistoletazo();
            p.resultadosCarrera();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
