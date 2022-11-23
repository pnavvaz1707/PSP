package Trimestre1.T03.Ejercicios.JuegoAdivinanza;

import java.io.IOException;

public class JuegoAdivinanza {

    private int numGenerado;
    boolean adivinado = false;

    private HiloAdivinanza ultimoJugadorAdivinando = null;

    public JuegoAdivinanza() {
        numGenerado = (int) (Math.random() * 10);
    }

    public synchronized String adivinarNumero(HiloAdivinanza jugadorAdivinando) throws IOException {
        while (jugadorAdivinando.equals(ultimoJugadorAdivinando)) {
            try {
                jugadorAdivinando.salida.writeUTF("Espere " + jugadorAdivinando.nombre + ", otro jugador está adivinando");
                System.err.println("Se ha intentao colar " + jugadorAdivinando.nombre);
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String mensaje;
        int adivinanza;

        System.out.println("El cliente va a adivinar...");

        jugadorAdivinando.salida.writeUTF("Adivina el número");

        adivinanza = Integer.parseInt(jugadorAdivinando.entrada.readUTF());
        System.out.println(jugadorAdivinando.nombre + " ha elegido " + adivinanza);

        if (adivinanza == numGenerado) {
            mensaje = "El cliente " + jugadorAdivinando.nombre + " ha ganado con el número (" + adivinanza + ")" +
                    "\nEl juego ha terminado";
            setAdivinado(true);

        } else if (adivinanza < numGenerado) {
            mensaje = "El número (" + adivinanza + ") introducido por el cliente " + jugadorAdivinando.nombre + " es menor" +
                    "\nEl juego continúa";

        } else {
            mensaje = "El número (" + adivinanza + ") introducido por el cliente " + jugadorAdivinando.nombre + " es mayor" +
                    "\nEl juego continúa";
        }
        ultimoJugadorAdivinando = jugadorAdivinando;
        notifyAll();
        return mensaje;
    }

    public synchronized boolean isAdivinado() {
        return adivinado;
    }

    public synchronized void setAdivinado(boolean adivinado) {
        this.adivinado = adivinado;
    }
}
