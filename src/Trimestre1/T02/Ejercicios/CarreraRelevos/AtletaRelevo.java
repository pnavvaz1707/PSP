package Trimestre1.T02.Ejercicios.CarreraRelevos;

public class AtletaRelevo extends Thread{

    int distanciaRecorrida = 0;
    PistaRelevo p;

    public AtletaRelevo(String name, PistaRelevo p){
        this.setName(name);
        this.p = p;
    }
    @Override
    public void run() {
        p.correr(this);
    }

    public int getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public void setDistanciaRecorrida(int distanciaRecorrida) {
        this.distanciaRecorrida = distanciaRecorrida;
    }
}
