package Trimestre1.T03.ExamenJunio;

public class ExamenJunioMain {
    public static void main(String[] args) {
        MaquinaExpendedora m = new MaquinaExpendedora(200);

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Botella(obtenerCapacidadRandom(), m));
            t.start();
        }
    }

    private static int obtenerCapacidadRandom() {
        return (int) (Math.random() * 76 + 25);
    }
}
