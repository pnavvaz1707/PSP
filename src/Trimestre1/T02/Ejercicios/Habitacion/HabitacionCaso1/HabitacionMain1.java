package Trimestre1.T02.Ejercicios.Habitacion.HabitacionCaso1;

public class HabitacionMain1 {
    public static void main(String[] args) {
        Habitacion1 habitacion = new Habitacion1();

        PersonaPuerta1 persona1 = new PersonaPuerta1("Alice", habitacion);
        PersonaPuerta1 persona2 = new PersonaPuerta1("Bob", habitacion);

        persona1.start();
        persona2.start();
    }
}
