package Trimestre1.T02.Ejercicios.StreetFighter;

public class Luchador extends Thread {
    int vida = 50;
    Ring r;
    boolean poderCargado = false;
    String accionElegida = "";
    boolean turno = false;

    public Luchador(String name, Ring r) {
        this.setName(name);
        this.r = r;
        this.r.addLuchador(this);
    }

    @Override
    public void run() {
        while (vida > 0 && !r.isTerminado()) {
            int opcion = (int) (Math.random() * 3 + 1);
            switch (opcion) {
                case 1:
                    r.defender(this);
                    break;
                case 2:
                    r.cargarPoder(this);
                    break;
                case 3:
                    r.golpear(this);
                    break;
            }
        }
    }

    public void restarVida(int resta) {
        this.vida -= resta;
    }

    public void setPoderCargado(boolean poderCargado) {
        this.poderCargado = poderCargado;
    }

    public void setAccionElegida(String accionElegida) {
        this.accionElegida = accionElegida;
    }

    public boolean isPoderCargado() {
        return poderCargado;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public boolean isTurno() {
        return turno;
    }

    public String getAccionElegida() {
        return accionElegida;
    }

    public int getVida() {
        return vida;
    }
}