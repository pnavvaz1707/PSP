package Trimestre1.T02.Ejercicios.Cruce;

import Trimestre1.T02.Colores;

public class CruceMain {
    public static void main(String[] args) {
        Cruce cruce = new Cruce();

        for (int i = 0; i < 10; i++) {
            Vehiculo vehiculo = new Vehiculo("Bicicleta " + i, TiposVehiculo.BICICLETA, cruce, Colores.ANSI_GREEN);
            vehiculo.setPriority(Thread.MAX_PRIORITY);
            vehiculo.start();
        }
        for (int i = 0; i < 10; i++) {
            Vehiculo vehiculo = new Vehiculo("Coche " + i, TiposVehiculo.COCHE, cruce,Colores.ANSI_YELLOW);
            vehiculo.setPriority(Thread.NORM_PRIORITY);
            vehiculo.start();
        }
    }
}
