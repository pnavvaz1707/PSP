package Trimestre1.T02.Ejercicios.Carpinteria.CarpinteriaCaso2;

import Trimestre1.T02.Colores;

public class Trabajo2 {
    public static void main(String[] args) {
        Utensilios2 u = new Utensilios2();

        Carpintero2 c1 = new Carpintero2("Carpintero 1", u, Colores.ANSI_PURPLE);
        Carpintero2 c2 = new Carpintero2("Carpintero 2", u, Colores.ANSI_BLUE);
        Carpintero2 c3 = new Carpintero2("Carpintero 3", u, Colores.ANSI_GREEN);

        c1.start();
        c2.start();
        c3.start();
    }
}
