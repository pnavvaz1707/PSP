package Trimestre1.ExamenesAntiguos.Examen1PRSP2223;

import java.io.IOException;

public class Crupier implements Runnable {

    double valorCartas;
    ConexionJugador jugadorAEnfrentar;
    Juego j;

    public Crupier(ConexionJugador jugadorAEnfrentar, Juego j) {
        this.jugadorAEnfrentar = jugadorAEnfrentar;
        this.j = j;
        this.valorCartas = 0;
    }

    @Override
    public void run() {
        try {
            while (valorCartas < jugadorAEnfrentar.valorCartas) {
                j.bancaJuega(this);
            }
            /* Hacemos que pierdan todos, por si hay alguno que se ha plantado y no lo hemos cambiado, luego
               en el caso de que el jugador gane de verdad le ponemos el estado a G en el if */
            j.pierdenTodos();
            if (valorCartas > 7.5) {
                jugadorAEnfrentar.salida.writeUTF("La banca pierde con " + valorCartas + " puntos");
                j.enviarMensajeAlResto(jugadorAEnfrentar, "La banca pierde con " + valorCartas + " puntos, gana el jugador " + jugadorAEnfrentar.nombre);
                jugadorAEnfrentar.estado = 'G';
            } else {
                jugadorAEnfrentar.salida.writeUTF("La banca gana con " + valorCartas + " puntos");
                j.enviarMensajeAlResto(jugadorAEnfrentar, "La banca gana con " + valorCartas + " puntos ante el jugador " + jugadorAEnfrentar.nombre);
            }
            j.terminado = true;
            System.out.println("Banca --> Valor final: " + valorCartas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
