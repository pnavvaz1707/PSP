package Trimestre1.T02.Ejercicios.Cruce;

import Trimestre1.T02.Colores;

import java.util.ArrayList;

public class Cruce {

    ArrayList<Vehiculo> bicicletas = new ArrayList<>();
    ArrayList<Vehiculo> coches = new ArrayList<>();
    ArrayList<Vehiculo> camiones = new ArrayList<>();

    int vehiculos = 0;

    public synchronized void llegar(Vehiculo vehiculo) {
        vehiculos++;
        imprimirTexto(vehiculo.getColor(), "Ha llegado " + vehiculo.getName());

        if (vehiculo.tipo == TiposVehiculo.BICICLETA) {
            bicicletas.add(vehiculo);

        } else if (vehiculo.tipo == TiposVehiculo.COCHE) {
            coches.add(vehiculo);

        } else {
            camiones.add(vehiculo);
        }
    }

    public synchronized void cruzar(Vehiculo vehiculo) {
        while (vehiculos < 20) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (vehiculo.tipo == TiposVehiculo.COCHE) {

            while (bicicletas.size() > 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            coches.remove(vehiculo);

        } else if (vehiculo.tipo == TiposVehiculo.CAMION) {

            while (bicicletas.size() > 0 || coches.size() > 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            camiones.remove(vehiculo);

        } else {

            bicicletas.remove(vehiculo);
        }
        imprimirTexto(vehiculo.getColor(), "----------------- Ha cruzado " + vehiculo.getName());
        notifyAll();
    }


    private static void imprimirTexto(String color, String msg) {
        System.out.println(color + msg + Colores.ANSI_RESET);
    }
}
