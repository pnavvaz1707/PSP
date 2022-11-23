package Trimestre1.T03.Ejercicios.SieteYMedio;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class JuegoSieteYMedio {
    MazoSieteYMedio mazo;
    ArrayList<JugadorSieteYMedio> jugadores;
    int numJugadores;
    boolean terminado = false;

    public JuegoSieteYMedio(int numJugadores) {
        this.numJugadores = numJugadores;
        this.jugadores = new ArrayList<>(numJugadores);
        this.mazo = new MazoSieteYMedio();
    }

    public synchronized void iniciarJuego(JugadorSieteYMedio jugador) {

        for (int i = 0; i < 2; i++) {
            CartaSieteYMedio carta = robarCarta();

            jugador.mano.add(carta);
            System.out.println("El jugador " + jugador.nombre + " ha robado " + carta);
        }

    }

    public synchronized void jugar(JugadorSieteYMedio jugador) throws IOException {
        while (jugadores.size() != numJugadores || jugador.haJugado) {
            try {
                System.err.println("Se ha querio colar " + jugador.nombre);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Está jugando " + jugador.nombre);

        StringBuilder mensajeCartas = new StringBuilder("¿Cuantas cartas deseas robar? (0 = ninguna)\n");
        mensajeCartas.append("Cartas actuales:");
        for (CartaSieteYMedio carta : jugador.mano) {
            mensajeCartas.append("\n\t-> ").append(carta);
        }

        jugador.salida.writeUTF(mensajeCartas.toString());

        int veces = Integer.parseInt(jugador.entrada.readUTF());

        for (int i = 0; i < veces; i++) {
            CartaSieteYMedio carta = robarCarta();

            jugador.mano.add(carta);
            System.out.println("El jugador " + jugador.nombre + " ha robado " + carta);
        }

        mensajeCartas = new StringBuilder();
        mensajeCartas.append("Cartas actuales:");
        for (CartaSieteYMedio carta : jugador.mano) {
            mensajeCartas.append("\n\t-> ").append(carta);
        }

        jugador.salida.writeUTF(mensajeCartas.toString());

        jugador.haJugado = true;

        if (comprobarTurnos()) {
            reiniciarRonda();
        }

        notifyAll();
    }

    public synchronized String mensajeFinal() {
        String mensajeFinal;

        if (terminado) {
            mensajeFinal = "Ya se ha terminado";
        } else {
            mensajeFinal = "Sigue la partida";
        }

        return mensajeFinal;
    }

    private CartaSieteYMedio robarCarta() {
        CartaSieteYMedio cartaRobada = this.mazo.cartas.get((int) (Math.random() * this.mazo.cartas.size()));
        this.mazo.cartas.remove(cartaRobada);
        return cartaRobada;
    }

    private boolean comprobarTurnos() {
        boolean reiniciar = true;
        int contador = 0;

        while (reiniciar && contador < jugadores.size()) {
            if (!jugadores.get(contador).haJugado) {
                reiniciar = false;
            }
            contador++;
        }
        return reiniciar;
    }

    private void reiniciarRonda() throws IOException {
        for (JugadorSieteYMedio jugador : jugadores) {
            jugador.haJugado = false;
        }
        for (JugadorSieteYMedio jugadore : jugadores) {
            DataOutputStream salida = new DataOutputStream(jugadore.conexion.getOutputStream());
            salida.writeUTF(mensajeFinal());
        }
    }

    public synchronized boolean isTerminado() {
        return terminado;
    }

    public synchronized int getNumJugadores() {
        return numJugadores;
    }

    public synchronized void regitrarse(JugadorSieteYMedio jugador) throws IOException {
        jugadores.add(jugador);

        System.out.println(jugador.nombre + " ha empezado a jugar");
    }
}
