package Trimestre1.T02.Ejercicios.peval1psp2223;

/**
 * Clase que se comporta como un hilo que recuenta los votos de la clase ColegioElectoral cada 10 segundos
 */
public class RecuentoVotos extends Thread {
    //Clase que hace de tubería entre los hilos de la clase Votante y esta clase
    ColegioElectoral c;

    /**
     * Constructor parametrizado de la clase RecuentoVotos
     *
     * @param c Clase que hace de tubería entre varios hilos
     */
    public RecuentoVotos(ColegioElectoral c) {
        this.c = c;
    }

    /**
     * Método sobreescrito de la clase Thread que ejecuta el método recontarVotos de la clase ColegioElectoral
     */
    @Override
    public void run() {
        c.recontarVotos();
    }
}
