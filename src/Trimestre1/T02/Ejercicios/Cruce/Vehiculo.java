package Trimestre1.T02.Ejercicios.Cruce;

public class Vehiculo extends Thread {
    TiposVehiculo tipo;
    Cruce cruce;
    String color;

    public Vehiculo(String nombre, TiposVehiculo tipo, Cruce cruce,String color) {
        this.setName(nombre);
        this.tipo = tipo;
        this.cruce = cruce;
        this.color = color;
    }

    @Override
    public void run() {
        cruce.llegar(this);
        cruce.cruzar(this);
    }

    public String getColor() {
        return color;
    }
}
