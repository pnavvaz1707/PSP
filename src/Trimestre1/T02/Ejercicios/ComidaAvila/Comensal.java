package Trimestre1.T02.Ejercicios.ComidaAvila;

public class Comensal extends Thread {

    Chuleton c;

    public Comensal(String nombre, Chuleton c) {
        this.setName(nombre);
        this.c = c;
    }

    @Override
    public void run() {
        try {
            while (c.gramos > 0) {
                c.pinchar(this);
                c.cortar(this);
                c.comer(this);
                Thread.sleep(comerChuleton());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private int comerChuleton() {
        return (int) (Math.random() * 400 + 200);
    }
}
