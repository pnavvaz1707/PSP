package Trimestre1.T02.Ejercicios.Cruce;

public class Vehiculo extends Thread {
    TiposVehiculo tipo;
    Cruce cruce;
    String color;

    public Vehiculo(String nombre, TiposVehiculo tipo, Cruce cruce, String color) {
        this.setName(nombre);
        this.tipo = tipo;
        this.cruce = cruce;
        this.color = color;
    }

    @Override
    public void run() {
        try {
            sleep(numRandom(1,10));
            cruce.llegar(this);
            sleep(numRandom(0,2));
            cruce.cruzar(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public String getColor() {
        return color;
    }

    private int numRandom(int limiteInferior, int limiteSuperior) {
        return (int) (Math.random() * ((limiteSuperior - limiteInferior) * 1000) + (limiteInferior * 1000));
    }
}
