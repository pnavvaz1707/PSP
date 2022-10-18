package Trimestre1.T01.Clase.Castillo;

public class Persona extends Thread{

    private Salon t;

    public Persona(String name,Salon t){
        this.setName(name);
        this.t=t;
        System.out.println("Hilo creado --> "  + this.getName());
    }

    @Override
    public void run() {
        t.saludar(this.getName());
    }
}
