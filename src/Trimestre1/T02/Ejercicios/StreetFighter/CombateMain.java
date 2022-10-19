package Trimestre1.T02.Ejercicios.StreetFighter;

public class CombateMain {
    public static void main(String[] args) {
        Ring r = new Ring();

        Combate c = new Combate(r);

        Luchador luchador1 = new Luchador("Luchador 1",r);
        Luchador luchador2 = new Luchador("Luchador 2",r);

        luchador1.start();
        luchador2.start();

        c.start();
    }
}
