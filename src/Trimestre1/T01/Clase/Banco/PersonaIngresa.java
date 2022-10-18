package Trimestre1.T01.Clase.Banco;

public class PersonaIngresa extends Thread{
    Banco b;

    public PersonaIngresa(String nombre, Banco b){
        this.b = b;
        this.setName(nombre);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            b.ingresar(this.getName(),500);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
