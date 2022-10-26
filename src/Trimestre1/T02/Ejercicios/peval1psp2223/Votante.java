package Trimestre1.T02.Ejercicios.peval1psp2223;

import Trimestre1.T02.Colores;

/**
 * Clase que se comporta como un hilo que comprueba si es apto para votar en la tubería y si lo es, vota
 */

public class Votante extends Thread {

    //Variable de tipo entero que almacena el dni de cada votante
    private int dni;
    //Clase que hace de tubería entre el hilo de la clase RecuentoVotos y esta clase
    ColegioElectoral c;
    //Booleano que sirve para saber si el votante puede votar o no puede votar
    private boolean puedeVotar;

    /**
     * Constructor parametrizado de la clase Votante
     *
     * @param c   Clase que hace de tubería entre varios hilos
     * @param dni Número entero que sirve como dni para cada Votante
     */
    public Votante(ColegioElectoral c, int dni) {
        this.dni = dni;
        this.c = c;
    }

    /**
     * Método sobreescrito de la clase Thread que imprime un mensaje cuando acaba de entrar al método y cuando va a salir. Al entrar ejecuta el método
     * estaEnElCenso de la clase ColegioElectoral y si la variable puedeVotar es true también entra al método votar de la clase ColegioElectoral
     */
    @Override
    public void run() {
        Colores.imprimirMorado("El votante con dni " + this.getDni() + " ha entrado al censo");
        c.estaEnElCenso(this);
        if (puedeVotar) {
            c.votar(this);
        }
        Colores.imprimirMorado("El votante con dni " + this.getDni() + " ha salido del censo");
    }

    /**
     * Método que sirve para obtener el dni de un votante
     *
     * @return número entero que hace referencia al dni del votante
     */
    public int getDni() {
        return dni;
    }

    /**
     * Método para indicar si un votante puede votar o no
     *
     * @param puedeVotar (Booleano que indica si el votante puede votar)
     */
    public void setPuedeVotar(boolean puedeVotar) {
        this.puedeVotar = puedeVotar;
    }
}
