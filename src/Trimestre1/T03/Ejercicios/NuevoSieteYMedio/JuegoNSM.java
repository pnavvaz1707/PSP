package Trimestre1.T03.Ejercicios.NuevoSieteYMedio;

import java.io.IOException;
import java.util.ArrayList;

public class JuegoNSM {

    BarajaSieteYMedio baraja;
    ArrayList<HiloServidorNSM> jugadores;
    int maxJugadores;
    static int turno = 0;
    boolean terminado;
    /*
    Escribe
    Lee
     */

    public JuegoNSM(int maxJugadores) {
        this.maxJugadores = maxJugadores;
        this.baraja = new BarajaSieteYMedio();
        this.jugadores = new ArrayList<>();
    }

    public synchronized void iniciarJuego(HiloServidorNSM jugador) {
        baraja.extraerCarta();
        jugadores.add(jugador);
    }

    public synchronized void jugar(HiloServidorNSM jugador) throws IOException {
        while (jugadores.size() != maxJugadores) {
            try {
                System.err.println("Espera que se estén todos (Jugadores actuales : " + jugadores.size() + ")");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        while (!jugadores.get(turno).equals(jugador)) {
            try {
                System.err.println("No es tu turno " + jugador.nombre + " (Turno actual: " + turno + " | Tu turno --> " + jugadores.indexOf(jugador) + ")");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Está jugando " + jugador.nombre);

        jugador.salida.writeUTF("¿Deseas robar carta o plantarte? (Robar = 1 | Plantarte = 0)");
        int eleccion = Integer.parseInt(jugador.entrada.readUTF());

        if (eleccion == 1) {
            String carta = baraja.extraerCarta();

            System.out.println("Ha robado " + carta);

            jugador.valorCartas += calcularValorCarta(carta);

            System.out.println("Valor jugador --> " + jugador.valorCartas);

            if (jugador.valorCartas == 7.5) {
                jugador.estado = 'G';
                terminado = true;
                turnoBanca(jugador);

            }

        } else {
            jugador.estado = 'C';
        }
        if (turno == jugadores.size() - 1) {

            turno = 0;
        } else {
            turno++;
        }

        notifyAll();
    }

    private double calcularValorCarta(String carta) {
        double valor;
        switch (carta.charAt(0)) {
            case '1', '2', '3', '4', '5', '6', '7':
                valor = 1;
                break;
            default:
                valor = 0.5;
                break;
        }
        return valor;
    }

    private void turnoBanca(HiloServidorNSM jugador) {
        double valorBanca;
        do {
            //todo da un error al robar muchas cartas pq no quedan de ese palo
            String carta = baraja.extraerCarta();
            System.out.println("La banca roba " + carta);

            valorBanca = calcularValorCarta(carta);
            System.out.println("Valor banca --> " + valorBanca);

            System.out.println("Valor jugador --> " + jugador.valorCartas);
        } while (valorBanca < jugador.valorCartas);

        if (valorBanca > 7.5) {
            System.out.println("La banca pierde");
        } else {
            System.out.println("La banca gana");
            jugador.estado = 'P';
        }
    }
}
