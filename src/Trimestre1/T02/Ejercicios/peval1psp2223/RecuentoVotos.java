package Trimestre1.T02.Ejercicios.peval1psp2223;

public class RecuentoVotos extends Thread {
    ColegioElectoral c;

    public RecuentoVotos(ColegioElectoral c) {
        this.c = c;
    }

    @Override
    public void run() {
        c.recontarVotos();
    }
}
