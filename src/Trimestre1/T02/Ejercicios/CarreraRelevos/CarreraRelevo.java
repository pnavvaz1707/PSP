package Trimestre1.T02.Ejercicios.CarreraRelevos;

public class CarreraRelevo {
    public static void main(String[] args) {
        PistaRelevo p = new PistaRelevo();

        AtletaRelevo dorsal1 = new AtletaRelevo("Dorsal 1", p);
        AtletaRelevo dorsal2 = new AtletaRelevo("Dorsal 2", p);
        AtletaRelevo dorsal3 = new AtletaRelevo("Dorsal 3", p);
        AtletaRelevo dorsal4 = new AtletaRelevo("Dorsal 4", p);

        dorsal1.start();
        dorsal2.start();
        dorsal3.start();
        dorsal4.start();

    }
}
