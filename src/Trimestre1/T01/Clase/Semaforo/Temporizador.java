package Trimestre1.T01.Clase.Semaforo;

public class Temporizador extends Thread{

    Semaforo s;

    public Temporizador(Semaforo s){
        this.s = s;
    }

    @Override
    public void run() {
        while (!s.isDetenido()) {
            s.restarTiempo();
        }
    }
}
