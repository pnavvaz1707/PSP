package Trimestre1.T02.Ejercicios.Carrera;

public class CarreraMain {
    public static void main(String[] args) {
        Pista p = new Pista();

        Carrera carrera = new Carrera(p);

        Atleta dorsal1 = new Atleta("Dorsal 1", p);
        Atleta dorsal2 = new Atleta("Dorsal 2", p);
        Atleta dorsal3 = new Atleta("Dorsal 3", p);
//        Atleta dorsal4 = new Atleta("Dorsal 4", p);
//        Atleta dorsal5 = new Atleta("Dorsal 5", p);
//        Atleta dorsal6 = new Atleta("Dorsal 6", p);
//        Atleta dorsal7 = new Atleta("Dorsal 7", p);
//        Atleta dorsal8 = new Atleta("Dorsal 8", p);

        carrera.start();

        dorsal1.start();
        dorsal2.start();
        dorsal3.start();
//        dorsal4.start();
//        dorsal5.start();
//        dorsal6.start();
//        dorsal7.start();
//        dorsal8.start();
    }
}
