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

    public synchronized void iniciarJuego(JugadorSieteYMedio jugador) throws IOException {
        CartaSieteYMedio carta = robarCarta();

        jugador.mano.add(carta);
        System.out.println("El jugador " + jugador.nombre + " ha robado " + carta);
    }

    public synchronized void jugar(JugadorSieteYMedio jugador) throws IOException {
        while (jugadores.size() != getNumJugadores() || jugador.haJugado) {
            try {
                System.err.println("Se ha querio colar " + jugador.nombre + " (Ha jugado = " + jugador.haJugado + ")");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!terminado) {
            System.out.println("Está jugando " + jugador.nombre);

            StringBuilder mensajeCartas = new StringBuilder("¿Cuantas cartas deseas robar? (0 = ninguna)\n");
            mensajeCartas.append("Cartas actuales:");
            for (CartaSieteYMedio carta : jugador.mano) {
                mensajeCartas.append("\n\t-> ").append(carta);
            }

            jugador.salida.writeUTF(mensajeCartas.toString());

            int veces = Integer.parseInt(jugador.entrada.readUTF());

            mensajeCartas = new StringBuilder();

            if (veces == 0) {
                jugador.estado = 'C';
                mensajeCartas.append("Te has plantado.\n");
            } else {
                CartaSieteYMedio carta = robarCarta();

                jugador.mano.add(carta);
                System.out.println("El jugador " + jugador.nombre + " ha robado " + carta);
            }

            double valorMano = sumarCartas(jugador);

            if (valorMano == 7.5) {
                jugador.estado = 'G';
                mensajeCartas.append("Has ganado\n");
                terminado = true;
            } else if (valorMano > 7.5) {
                jugador.estado = 'P';
            }

            mensajeCartas.append("Cartas actuales:");

            for (CartaSieteYMedio carta : jugador.mano) {
                mensajeCartas.append("\n\t-> ").append(carta);
            }

            jugador.salida.writeUTF(mensajeCartas.toString());

            jugador.haJugado = true;

            if (comprobarTurnos()) {
                reiniciarRonda();
            }
        } else {
            if (jugador.estado == 'G') {
                System.err.println("Q has ganao " + jugador.nombre);
            } else {
                jugador.estado = 'P';
                System.err.println("Has perdio " + jugador.nombre);
                jugador.conexion.close();
            }
        }
        notifyAll();
    }

    private CartaSieteYMedio robarCarta() {
        CartaSieteYMedio cartaRobada = this.mazo.cartas.get((int) (Math.random() * this.mazo.cartas.size()));
        this.mazo.cartas.remove(cartaRobada);
        return cartaRobada;
    }

    private double sumarCartas(JugadorSieteYMedio jugador) {
        double sumaTotal = 0;
        for (CartaSieteYMedio carta : jugador.mano) {
            sumaTotal += carta.getValor();
        }
        return sumaTotal;
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
        String mensajeFinalRonda = "La partida sigue";
        String mensajeFinalJuego = "La partida ha terminado";

        for (JugadorSieteYMedio jugador : jugadores) {
            if (jugador.estado == 'G') {
                mensajeFinalJuego += "\nHa ganado --> " + jugador.nombre;
            } else {
                jugador.haJugado = false;
                System.out.println("\t--> Se ha reseteado el jugador " + jugador.nombre);
            }
        }

        if (terminado) {
            enviarMensaje(mensajeFinalJuego);
        } else {
            System.out.println("Se ha reiniciado la ronda");
            enviarMensaje(mensajeFinalRonda);
        }
    }


    private void enviarMensaje(String mensaje) throws IOException {
        for (JugadorSieteYMedio jugador : jugadores) {
            DataOutputStream salida = new DataOutputStream(jugador.conexion.getOutputStream());

            salida.writeUTF(mensaje);
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
