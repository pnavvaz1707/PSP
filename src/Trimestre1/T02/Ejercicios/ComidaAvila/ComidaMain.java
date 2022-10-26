package Trimestre1.T02.Ejercicios.ComidaAvila;

public class ComidaMain {
    public static void main(String[] args) {
        Chuleton c = new Chuleton();

        for (int i = 0; i < 2; i++) {
            Comensal comensal = new Comensal("Comensal " + i,c);
            comensal.start();
        }
    }
}
