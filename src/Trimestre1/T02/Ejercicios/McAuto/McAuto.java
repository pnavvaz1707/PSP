package Trimestre1.T02.Ejercicios.McAuto;

public class McAuto {
    public static void main(String[] args) {
        Ventanilla ventanilla = new Ventanilla();

        for (int i = 0; i < 30; i++) {
            Thread cliente = new Thread(new Cliente(("Cliente " + i), ventanilla));
            cliente.start();
        }

    }
}
