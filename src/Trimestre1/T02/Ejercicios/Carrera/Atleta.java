package Trimestre1.T02.Ejercicios.Carrera;

public class Atleta extends Thread {

    Pista p;
    int distancaRecorrida = 0;

    int tiempoEmpleado = 0;

    public Atleta(String name, Pista p) {
        this.setName(name);
        this.p = p;
        p.sumarAtleta();
    }

    @Override
    public void run() {
        while (distancaRecorrida<p.getDistanciaPista()) {
            p.correr(this);
            distancaRecorrida++;
            System.out.println(this.getName() + " - " + distancaRecorrida + "m");
        }
        p.notificarDorsal(this);
    }

    public void setTiempoEmpleado(int tiempoEmpleado) {
        this.tiempoEmpleado += tiempoEmpleado;
    }

    public int getTiempoEmpleado() {
        return tiempoEmpleado;
    }
}
