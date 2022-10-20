package Trimestre1.T02.Ejercicios.Supermercado;

public class SupermercadoMain {
    public static void main(String[] args) {
        Supermercado supermercado = new Supermercado();

        for (int i = 0; i < 30; i++) {
            Thread cliente = new Thread(new Cliente(("Cliente " + i), supermercado));
            cliente.start();
        }

    }
}
