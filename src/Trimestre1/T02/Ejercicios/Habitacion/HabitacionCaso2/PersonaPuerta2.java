package Trimestre1.T02.Ejercicios.Habitacion.HabitacionCaso2;

public class PersonaPuerta2 extends Thread {

    Habitacion2 h;

    public PersonaPuerta2(String name, Habitacion2 h) {
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
