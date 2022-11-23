package Trimestre1.T03.Ejercicios.SieteYMedio;

import java.util.ArrayList;
import java.util.Collections;

public class MazoSieteYMedio {
    ArrayList<CartaSieteYMedio> cartas = new ArrayList<>();

    public MazoSieteYMedio() {
        for (int i = 0; i < PaloSieteYMedio.values().length; i++) {
            cartas.add(new CartaSieteYMedio(PaloSieteYMedio.values()[i], "A", 1));
            for (int j = 2; j <= 10; j++) {
                cartas.add(new CartaSieteYMedio(PaloSieteYMedio.values()[i], 1));
            }
            cartas.add(new CartaSieteYMedio(PaloSieteYMedio.values()[i], "J", 0.5));
            cartas.add(new CartaSieteYMedio(PaloSieteYMedio.values()[i], "Q", 0.5));
            cartas.add(new CartaSieteYMedio(PaloSieteYMedio.values()[i], "K", 0.5));
        }
    }

//    public void robar(ArrayList<JugadorB> jugadores, int veces) {
//        ArrayList<Carta> cartasEscogidas = new ArrayList<>();
//        boolean quedanCartas = true;
//        int i = 0;
//        while (i < jugadores.size() && quedanCartas) {
//            for (int j = 0; j < veces; j++) {
//                boolean sigue = true;
//                while (sigue && !(cartasEscogidas.size() == this.cartas.size())) {
//                    Carta carta = this.cartas.get((int) (Math.random() * this.cartas.size()));
//                    if (!carta.isEscogida()) {
//                        jugadores.get(i).cartas.add(carta);
//                        carta.escoger();
//                        cartasEscogidas.add(carta);
//                        sigue = false;
//                    }
//                }
//                if (cartasEscogidas.size() == this.cartas.size()) {
//                    quedanCartas = false;
//                }
//            }
//            i++;
//        }
//        if (!quedanCartas){
//            System.out.println("No quedan cartas");
//        }
//    }

}
