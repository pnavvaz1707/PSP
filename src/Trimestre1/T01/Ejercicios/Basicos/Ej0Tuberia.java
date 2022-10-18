package Trimestre1.T01.Ejercicios.Basicos;

public class Ej0Tuberia {

    char[] buffer;
    int siguiente;

    String ultimoHilo = "";
    boolean estaLLeno = false;
    boolean estaVacio = true;

    public Ej0Tuberia() {
        this.buffer = new char[6];
        siguiente = 0;
    }

    public synchronized void lanzar(char c) {
        while (!estaVacio) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Lanzando carácter " + c + " (" + siguiente + ")");
        buffer[siguiente] = c;
        this.siguiente++;
        estaVacio = false;
        if (siguiente == buffer.length) {
            estaLLeno = true;
        }
        notifyAll();
    }

    public synchronized void recoger() {
        while (!estaVacio) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("He recogido el carácter --> " + buffer[siguiente]);
        this.siguiente--;
        estaLLeno = false;
        if (siguiente == 0) {
            estaVacio = true;
        }
        notifyAll();
    }
}
