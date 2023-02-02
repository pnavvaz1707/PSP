package Trimestre1.T02.Ejercicios.McAuto;

public class Camarero implements Runnable {

    Macdonal mac;

    public Camarero(Macdonal mac) {

        this.mac = mac;
        System.out.println("Camarero creado");
    }

    @Override
    public void run() {
        while (true) {
            mac.cocinar();
        }
//        try {
//            while (mac.cocinaAbierta) {
//                if (mac.colaRecoger.size() > 0) {
//                    mac.cocinar(this);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

}