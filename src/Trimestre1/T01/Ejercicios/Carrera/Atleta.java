package Trimestre1.T01.Ejercicios.Carrera;

public class Atleta extends Thread{

    Pista p;

    public Atleta(String name, Pista p){
        this.setName(name);
        this.p = p;
    }

    @Override
    public void run() {
        super.run();
    }
}
