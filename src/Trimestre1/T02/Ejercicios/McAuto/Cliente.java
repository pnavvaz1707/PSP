package Trimestre1.T02.Ejercicios.McAuto;

public class Cliente implements Runnable{

    Macdonal mac;
    String nombre;

    public Cliente(Macdonal mac,String nombre) {

        this.mac = mac;
        this.nombre = nombre;
        //System.out.println(nombre+" creado.");
    }

    @Override
    public void run() {
        try {
            mac.ponerseEnCola(this);
            mac.pedir(this);
            //mac.esperarPedido(this,nombre);
            mac.recoger(this);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
