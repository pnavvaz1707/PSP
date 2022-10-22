package Trimestre1.T02.Ejercicios.Cruce;

import Trimestre1.T02.Colores;

import java.util.ArrayList;

public class Cruce {

    ArrayList<Vehiculo> bicicletas = new ArrayList<>();
    ArrayList<Vehiculo> coches = new ArrayList<>();
    ArrayList<Vehiculo> camiones = new ArrayList<>();

    public void llegar(Vehiculo vehiculo) {

        if (vehiculo.tipo == TiposVehiculo.BICICLETA) {
            getBicicletas().add(vehiculo);

        } else if (vehiculo.tipo == TiposVehiculo.COCHE) {
            getCoches().add(vehiculo);

        } else {
            getCamiones().add(vehiculo);
        }

        imprimirTexto(vehiculo.getColor(), "Ha llegado " + vehiculo.getName());
    }

    public synchronized void cruzar(Vehiculo vehiculo) {
        if (vehiculo.tipo == TiposVehiculo.COCHE) {

            while (getBicicletas().size() > 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            getCoches().remove(vehiculo);

        } else if (vehiculo.tipo == TiposVehiculo.CAMION) {

            while (getBicicletas().size() > 0 || getCoches().size() > 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            getCamiones().remove(vehiculo);

        } else {

            getBicicletas().remove(vehiculo);
        }
        imprimirTexto(vehiculo.getColor(), "----------------- Ha cruzado " + vehiculo.getName());
        notifyAll();
    }


    public synchronized ArrayList<Vehiculo> getBicicletas() {
        return bicicletas;
    }

    public synchronized ArrayList<Vehiculo> getCoches() {
        return coches;
    }

    public synchronized ArrayList<Vehiculo> getCamiones() {
        return camiones;
    }


    private static void imprimirTexto(String color, String msg) {
        System.out.println(color + msg + Colores.ANSI_RESET);
    }
}
