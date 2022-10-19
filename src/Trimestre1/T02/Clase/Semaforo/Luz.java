package Trimestre1.T02.Clase.Semaforo;

public class Luz extends Thread {

    Semaforo s;

    boolean encendido = false;


    public Luz(Semaforo s, String color) {
        this.s = s;
        this.setName(color);
    }

    @Override
    public void run() {
        while (!s.isDetenido()) {
            s.encenderLuz(this);
            s.apagarLuz(this);
        }
    }
}
