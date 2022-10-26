package Trimestre1.T02.Ejercicios.ComidaAvila;

public class Chuleton {

    Comensal personaComiendo = null;
    int cantidad;
    int gramos = 500;


    public synchronized void pinchar(Comensal comensal) {
        while (personaComiendo != null){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        personaComiendo = comensal;
        System.out.println(comensal.getName() + " ha pinchado el trozo");
    }

    public synchronized void cortar(Comensal comensal) {
        while (personaComiendo != comensal) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        cantidad = gramosChuleton();
        if (cantidad > gramos) {
            cantidad = gramos;
        }
        System.out.println("Ha cortado " + cantidad + " gramos");
    }

    public synchronized void comer(Comensal comensal) {
        while (personaComiendo != comensal){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        gramos -= cantidad;
        System.out.println("Quedan " + gramos + " gramos");
        personaComiendo = null;
        notifyAll();
    }

    private int gramosChuleton() {
        return (int) (Math.random() * 30 + 10);
    }
}
