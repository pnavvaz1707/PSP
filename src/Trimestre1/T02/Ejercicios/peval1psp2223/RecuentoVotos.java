package Trimestre1.T02.Ejercicios.peval1psp2223;

public class RecuentoVotos extends Thread {
    Censo c;

    public RecuentoVotos(Censo c) {
        this.c = c;
    }

    @Override
    public void run() {
        while (true) {
            c.recontarVotos();
        }
    }
}
