package Trimestre1.T02.Ejercicios.Cruce;

import Trimestre1.T02.Ejercicios.peval1psp2223.Colores;

public class CruceMain {
    public static void main(String[] args) {
        Cruce cruce = new Cruce();

        for (int i = 0; i < 10; i++) {
            Vehiculo vehiculo = new Vehiculo("Coche " + i, TiposVehiculo.COCHE, cruce,Colores.ANSI_YELLOW);
            vehiculo.setPriority(Thread.MAX_PRIORITY);
            vehiculo.start();
        }
        for (int i = 0; i < 10; i++) {
            Vehiculo vehiculo = new Vehiculo("Camión " + i, TiposVehiculo.CAMION, cruce, Colores.ANSI_BLUE);
            vehiculo.setPriority(Thread.MAX_PRIORITY);
            vehiculo.start();
        }
        for (int i = 0; i < 10; i++) {
            Vehiculo vehiculo = new Vehiculo("Bicicleta " + i, TiposVehiculo.BICICLETA, cruce, Colores.ANSI_GREEN);
            vehiculo.setPriority(Thread.MIN_PRIORITY);
            vehiculo.start();
        }
    }
}
