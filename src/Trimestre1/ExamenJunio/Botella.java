package Trimestre1.ExamenJunio;

public class Botella implements Runnable{

    int capacidad; //Capacidad de la botella en centilitros
    MaquinaExpendedora m;

    public Botella(int capacidad, MaquinaExpendedora m) {
        this.capacidad = capacidad;
        this.m = m;
    }

    @Override
    public void run() {
        m.rellenarBotella(this);
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}
