package Trimestre1.T02.Ejercicios.Habitacion.HabitacionCaso3;

public class PersonaPuerta3 extends Thread {

    Habitacion3 h;

    public PersonaPuerta3(String name, Habitacion3 h) {
        this.setName(name);
        this.h = h;
    }

    @Override
    public void run() {
        int numRandom;

        for (int i = 0; i < 20; i++) {

            numRandom = numRandom();

            if (numRandom == 1) {

                h.abrir(this.getName());

            } else {

                h.cerrar(this.getName());
            }
        }
    }

    private int numRandom() {
        return (int) ((Math.random() * 2) + 1);
    }
}
