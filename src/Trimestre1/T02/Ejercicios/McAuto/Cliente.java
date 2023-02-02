package Trimestre1.T02.Ejercicios.McAuto;

public class Cliente implements Runnable{

    Ventanilla ventanillla;
    String nombre;

    public Cliente(String nombre, Ventanilla ventanillla){
        this.ventanillla = ventanillla;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        ventanillla.ponerEnCola(nombre);
        ventanillla.pedir(nombre);
        ventanillla.pagar(nombre);
    }
}
