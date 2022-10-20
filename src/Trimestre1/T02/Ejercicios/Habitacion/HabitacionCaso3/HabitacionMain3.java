package Trimestre1.T02.Ejercicios.Habitacion.HabitacionCaso3;

public class HabitacionMain3 {
    public static void main(String[] args) {
        Habitacion3 habitacion = new Habitacion3();

        PersonaPuerta3 persona1 = new PersonaPuerta3("Alice", habitacion);
        PersonaPuerta3 persona2 = new PersonaPuerta3("Bob", habitacion);

        persona1.start();
        persona2.start();
    }
}
