package Trimestre1.T02.Ejercicios.Carpinteria.CarpinteriaCaso1;

import Trimestre1.T02.Colores;

public class Trabajo1 {
    public static void main(String[] args) {
        Utensilios1 u = new Utensilios1();

        Carpintero1 c1 = new Carpintero1("Carpintero 1", u, Colores.ANSI_PURPLE);
        Carpintero1 c2 = new Carpintero1("Carpintero 2", u, Colores.ANSI_BLUE);
        Carpintero1 c3 = new Carpintero1("Carpintero 3", u, Colores.ANSI_GREEN);

        c1.start();
        c2.start();
        c3.start();
    }
}
