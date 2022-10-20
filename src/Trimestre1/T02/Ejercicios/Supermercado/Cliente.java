package Trimestre1.T02.Ejercicios.Supermercado;

public class Cliente implements Runnable{

    Supermercado s;
    String nombre;

    public Cliente(String nombre, Supermercado s){
        this.s = s;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        s.comprar(nombre);
        s.ponerEnCola(nombre);
        s.pagar(nombre);
    }
}
