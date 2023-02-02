package Trimestre1.ExamenesAntiguos.Examen1PRSP2223;

import java.io.IOException;
import java.util.ArrayList;

public class Juego {

    Baraja baraja;
    ArrayList<ConexionJugador> jugadores;
    ArrayList<ConexionJugador> jugadoresTerminados;
    int maxJugadores;
    static int turno = 0;
    boolean ultimaRonda;
    boolean terminado;
    int jugadoresActuales = 0;

    public Juego(int maxJugadores) {
        this.maxJugadores = maxJugadores;
        this.baraja = new Baraja();
        this.jugadores = new ArrayList<>();
        this.jugadoresTerminados = new ArrayList<>();
    }

    public synchronized void iniciarJuego(ConexionJugador jugador) throws IOException {
        String cartaInicial = robarCarta();
        jugador.valorCartas += calcularValorCarta(cartaInicial);
        jugadores.add(jugador);
        jugadoresActuales++;
        jugador.salida.writeUTF("Has empezado la partida con un " + cartaInicial);
    }

    public synchronized void jugar(ConexionJugador jugador) throws IOException {
        while (jugadoresActuales != maxJugadores) {
            try {
                //Se esperan a que estén todos
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();

        while (!jugadores.get(turno).equals(jugador) && !jugadoresTerminados.contains(jugador)) {
            try {
                //Se espera si no es el turno del jugador y si ya ha terminado
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Si es la última ronda, es decir alguien ha llegado a 7.5, no hace nada y espera los resultados directamente
        if (!ultimaRonda) {

            int eleccion = (int) (Math.random() * 2 + 1);
            // De esta forma se haría sin random

//            if (jugador.valorCartas < 5.5) {
//                eleccion = 1;
//            }

            jugador.salida.writeUTF("Puntos actuales --> " + jugador.valorCartas);

            // Si no es aleatorio, si es menor que 5.5 sigue robando, sino se planta
            if (eleccion == 1) {
                String cartaRobada = robarCarta();

                jugador.valorCartas += calcularValorCarta(cartaRobada);

                jugador.salida.writeUTF("Has robado la carta " + cartaRobada + "\n" +
                        "Tienes actualmente " + jugador.valorCartas + " puntos");

                if (jugador.valorCartas == 7.5) {
                    jugador.estado = 'G';
                    ultimaRonda = true;
                    turnoBanca(jugador);

                } else if (jugador.valorCartas > 7.5) {
                    jugador.estado = 'P';
                    jugador.salida.writeUTF("Te has pasado");
                    eliminarJugador(jugador);
                }
            } else {
                jugador.estado = 'C';
                jugador.salida.writeUTF("Te has plantado, espera que terminen los demás");
                eliminarJugador(jugador);

            }
            //Esta condición es para que no entre el jugador que ha sacado siete y medio, porque si entrase jugaría 2 veces con la banca
            if (jugador.estado != 'G') {

                if (turno < jugadores.size() - 1) {
                    turno++;

                } else {
                    turno = 0;
                }

                if (jugadoresTerminados.size() == maxJugadores) {
                    //Creamos un jugador vacío auxiliar para almacenar el jugador con más puntos en él
                    ConexionJugador jugadorGanador = new ConexionJugador();

                    for (ConexionJugador jugadorTerminado : jugadoresTerminados) {

                        if (jugadorTerminado.estado == 'C') {

                            if (jugadorTerminado.valorCartas > jugadorGanador.valorCartas) {
                                jugadorGanador = jugadorTerminado;

                            } else if (jugadorTerminado.valorCartas < jugadorGanador.valorCartas) {
                                jugadorTerminado.estado = 'P';

                            } else {//Si hay empate se decide aleatorio

                                jugadorTerminado.estado = 'G';
                            }
                        }
                    }
                    //Si todos se han pasado jugadorGanador será null, por lo tanto nadie juega contra la banca
                    if (jugadorGanador.nombre != null) {
                        turnoBanca(jugadorGanador);
                    }else {
                        terminado = true;
                    }
                }
            }
        }
        notifyAll();
    }

    public void pierdenTodos() {
        jugadoresTerminados.addAll(jugadores);
        for (ConexionJugador jugadoresTerminado : jugadoresTerminados) {
            jugadoresTerminado.estado = 'P';
        }
    }

    public synchronized void bancaJuega(Crupier crupier) throws IOException {
        String carta = robarCarta();
        System.out.println("La banca roba " + carta);

        crupier.valorCartas += calcularValorCarta(carta);

        System.out.println("Valor banca --> " + crupier.valorCartas);
        System.out.println("Valor jugador (" + crupier.jugadorAEnfrentar.nombre + ") --> " + crupier.jugadorAEnfrentar.valorCartas);
        notifyAll();
    }

    private void turnoBanca(ConexionJugador jugador) throws IOException {

        jugador.salida.writeUTF("Vas a jugar contra la banca");

        enviarMensajeAlResto(jugador, "El jugador " + jugador.nombre + " va a jugar contra la banca");

        Thread crupier = new Thread(new Crupier(jugador, this));
        crupier.start();
    }

    public void enviarMensajeAlResto(ConexionJugador jugador, String msg) throws IOException {
        for (ConexionJugador jugadorTerminado : jugadoresTerminados) {
            if (jugadorTerminado != jugador) {
                jugadorTerminado.salida.writeUTF(msg);
            }
        }
    }

    public synchronized void esperarResultado(ConexionJugador jugador) throws IOException {
        while (jugadoresTerminados.size() != maxJugadores || !terminado) {
            try {
                //Se esperan a que terminen todos y a que, si hay alguien contra la banca, esperana  que termine también
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
        System.out.println(jugador.nombre + " --> Valor final: " + jugador.valorCartas + " || Estado = " + jugador.estado);
        notifyAll();
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

    private void eliminarJugador(ConexionJugador jugador) {
        /*
        Borramos el jugador del array en el que están los que siguen jugando y lo metemos en el de jugadores que
        han terminado y le restamos uno al turno para que no haya problemas de saltos de turno al borrarlo
         */
        jugadores.remove(jugador);
        jugadoresTerminados.add(jugador);
        turno -= 1;
    }

    private double calcularValorCarta(String carta) {
        double valor;
        try {
            valor = Integer.parseInt(String.valueOf(carta.charAt(0)));
        } catch (NumberFormatException e) {
            valor = 0.5;
        }
        return valor;
    }
}
