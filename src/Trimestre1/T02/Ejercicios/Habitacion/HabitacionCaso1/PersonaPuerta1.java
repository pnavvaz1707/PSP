package Trimestre1.T02.Ejercicios.Habitacion.HabitacionCaso1;

public class PersonaPuerta1 extends Thread {

    Habitacion1 h;

    public PersonaPuerta1(String name, Habitacion1 h) {
        this.setName(name);
        this.h = h;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            h.abrir(this.getName());
            h.cerrar(this.getName());
        }
    }
}
