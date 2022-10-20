package Trimestre1.T02.Ejercicios.Habitacion.HabitacionCaso2;

public class HabitacionMain2 {
    public static void main(String[] args) {
        Habitacion2 habitacion = new Habitacion2();

        PersonaPuerta2 persona1 = new PersonaPuerta2("Alice", habitacion);
        PersonaPuerta2 persona2 = new PersonaPuerta2("Bob", habitacion);

        persona1.start();
        persona2.start();
    }
}
