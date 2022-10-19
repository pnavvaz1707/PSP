package Trimestre1.T02.Clase.Banco;

public class PersonaRetira extends Thread {

    Banco b;

    public PersonaRetira(String nombre, Banco b){
        this.b = b;
        this.setName(nombre);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            b.retirar(this.getName(),500);
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
