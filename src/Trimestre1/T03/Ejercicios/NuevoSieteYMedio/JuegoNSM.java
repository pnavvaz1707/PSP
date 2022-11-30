package Trimestre1.T03.Ejercicios.NuevoSieteYMedio;

import Trimestre1.T02.Ejercicios.peval1psp2223.Colores;

import java.io.IOException;
import java.util.ArrayList;

public class JuegoNSM {

    BarajaSieteYMedio baraja;
    ArrayList<ConexionJugadorNSM> jugadores;
    ArrayList<ConexionJugadorNSM> jugadoresTerminados;
    int maxJugadores;
    static int turno = 0;
    boolean ultimaRonda;
    boolean terminado;
    int jugadoresActuales = 0;

    public JuegoNSM(int maxJugadores) {
        this.maxJugadores = maxJugadores;
        this.baraja = new BarajaSieteYMedio();
        this.jugadores = new ArrayList<>();
        this.jugadoresTerminados = new ArrayList<>();
    }

    public synchronized void iniciarJuego(ConexionJugadorNSM jugador) {
        String cartaInicial = robarCarta();
        jugador.valorCartas += calcularValorCarta(cartaInicial);
        jugadores.add(jugador);
        jugadoresActuales++;
    }

    public synchronized void jugar(ConexionJugadorNSM jugador) throws IOException {
        while (jugadoresActuales != maxJugadores) {
            try {
                System.err.println("Espera " + jugador.nombre + " que se estén todos (Jugadores actuales : " + jugadores.size() + ")");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        while (!jugadores.get(turno).equals(jugador) && !jugadoresTerminados.contains(jugador)) {
            try {
                System.err.println("No es tu turno " + jugador.nombre + " (Turno actual: " + turno + " | Tu turno --> " + jugadores.indexOf(jugador) + ")");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!ultimaRonda) {

            jugador.salida.writeUTF("¿Deseas robar carta o plantarte? (Robar = 1 | Plantarte = 0)\n" +
                    "Puntos actuales: " + jugador.valorCartas);

            int eleccion = Integer.parseInt(jugador.entrada.readUTF());

            if (eleccion == 1) {
                String cartaRobada = robarCarta();

                jugador.valorCartas += calcularValorCarta(cartaRobada);

                jugador.salida.writeUTF("Has robado la carta " + cartaRobada + "\n" +
                        "Tienes actualmente " + jugador.valorCartas + " puntos");

                System.out.println("Valor jugador " + jugador.nombre + " --> " + jugador.valorCartas);

                if (jugador.valorCartas == 7.5) {
                    darGanador(jugador);
                    turnoBanca(jugador);

                } else if (jugador.valorCartas > 7.5) {
                    jugador.estado = 'P';
                    eliminarJugador(jugador);
                }
            } else {
                jugador.estado = 'C';
                eliminarJugador(jugador);

            }
            if (!terminado) {
                if (turno < jugadores.size() - 1) {
                    turno++;

                } else {
                    turno = 0;
                }
                if (jugadoresTerminados.size() == maxJugadores) {
                    ConexionJugadorNSM jugadorGanador = new ConexionJugadorNSM();

                    for (ConexionJugadorNSM jugadorTerminado : jugadoresTerminados) {

                        if (jugadorTerminado.estado == 'C' || jugadorTerminado.estado == 'G') {
                            System.out.println("Jugador antes --> " + jugadorGanador.valorCartas);
                            System.out.println("Jugador " + jugadorTerminado.nombre + " --> " + jugadorTerminado.valorCartas);
                            if (jugadorTerminado.valorCartas > jugadorGanador.valorCartas) {
                                jugadorGanador = jugadorTerminado;
                            } else if (jugadorTerminado.valorCartas < jugadorGanador.valorCartas) {
                                jugadorTerminado.estado = 'P';
                            } else {
                                int numRandom = (int) (Math.random() * 2 + 1);
                                if (numRandom == 1) {
                                    jugadorGanador = jugadorTerminado;
                                }
                            }
                        }
                    }

                    if (jugadorGanador.nombre != null) {

                        System.out.println("Ha ganado " + jugadorGanador.nombre + " con " + jugadorGanador.valorCartas);
                        turnoBanca(jugadorGanador);
                    } else {
                        System.out.println("Han perdido todos");
                    }
                }
            }
        }
        notifyAll();
    }

    private void darGanador(ConexionJugadorNSM jugador) {
        jugadoresTerminados.addAll(jugadores);
        for (ConexionJugadorNSM jugadoresTerminado : jugadoresTerminados) {
            jugadoresTerminado.estado = 'P';
        }
        jugador.estado = 'G';
        ultimaRonda = true;
    }

    private void turnoBanca(ConexionJugadorNSM jugador) {
        double valorBanca = 0;
        do {
            String carta = robarCarta();

            System.out.println("La banca roba " + carta);

            valorBanca += calcularValorCarta(carta);
            System.out.println("Valor banca --> " + valorBanca);

            System.out.println("Valor jugador (" + jugador.nombre + ") --> " + jugador.valorCartas);
        } while (valorBanca < jugador.valorCartas);

        if (valorBanca > 7.5) {
            System.out.println("La banca ha perdido");
        } else {
            System.out.println("La banca ha ganado");
            jugador.estado = 'P';
        }
        terminado = true;
    }

    public synchronized void esperarResultado(ConexionJugadorNSM jugador) throws IOException {
        while (jugadoresTerminados.size() != maxJugadores) {
            try {
                System.err.println("Espera resultados (Jugadores que han terminado: " + jugadoresTerminados.size() + ", son " + maxJugadores + " ha terminado = " + ultimaRonda + ")");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (jugador.estado == 'G') {
            jugador.salida.writeUTF("Has ganado");
        } else {
            jugador.salida.writeUTF("Has perdido");
        }
    }

    private String robarCarta() {
        String carta;
        do {
            try {
                carta = baraja.extraerCarta();
            } catch (IndexOutOfBoundsException e) {
                carta = null;
            }
        } while (carta == null);

        return carta;
    }

    private void eliminarJugador(ConexionJugadorNSM jugador) {
        jugadores.remove(jugador);
        jugadoresTerminados.add(jugador);
        turno -= 1;
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
}
