package Trimestre1.T02.Ejercicios.peval1psp2223;

public class Votacion {
    public static void main(String[] args) {
        Censo c = new Censo();

        RecuentoVotos r = new RecuentoVotos(c);
        r.start();

        for (int i = 1; i <= 10; i++) {
            Votante votante = new Votante(c,i);
            votante.start();
        }
    }
}
