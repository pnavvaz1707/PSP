package Trimestre1.T02.Clase.Semaforo;

public class SemaforoMain {
    public static void main(String[] args) {

        Semaforo s = new Semaforo();

        Luz verde = new Luz(s,"Verde");
        Luz amarillo = new Luz(s,"Amarillo");
        Luz rojo = new Luz(s,"Rojo");

        Temporizador t = new Temporizador(s);

        t.start();

        verde.start();
        amarillo.start();
        rojo.start();
    }
}
