package Trimestre1.T02.Clase.Castillo;

public class Main {
    public static void main(String[] args) {
        Salon s = new Salon();

        Persona rey = new Persona("Rey",s);
        Persona caballero1 = new Persona("Caballero 1",s);
        Persona caballero2 = new Persona("Caballero 2",s);
        Persona caballero3 = new Persona("Caballero 3",s);

        caballero1.start();
        caballero2.start();
        rey.start();
        caballero3.start();
    }
}
