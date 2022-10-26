package Trimestre1.T02.Ejercicios.peval1psp2223;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que actúa como tubería entre los hilos Votante y RecuentoVotos
 */
public class ColegioElectoral {

    //ArrayList de números enteros que contiene los ids de los votantes permitidos para votar
    ArrayList<Integer> ids = new ArrayList<>(List.of(1, 2, 3, 4, 6, 8, 10, 11, 13, 14, 15, 18, 19, 20, 22, 23, 24, 28, 29, 30));
    //ArrayList de números enteros que contiene los ids de los votantes que están en la cola para votar
    ArrayList<Integer> cola = new ArrayList<>();
    //Booleano que indica si el hilo RecuentoVotos está recontando o no
    boolean recontando = false;
    //Variable de tipo entero que nos permite saber el número de votos que se han realizado
    int numVotos = 0;

    /**
     * Método para comprobar si el id del votante pasado por parámetro está incluido en el ArrayList de ids permitidos para votar. Si no está
     * incluido se modifica la variable puedeVotar del votante enviado como parámetro a false, en cambio, si está incluido la modifica a true
     *
     * @param votante (Hilo de la clase Votante que comprueba si es apto para votar o no)
     */
    public void estaEnElCenso(Votante votante) {
        Colores.imprimirAzul("El votante con dni " + votante.getDni() + " empieza a mirar si esta en el censo");
        if (ids.contains(votante.getDni())) {
            getCola().add(votante.getDni());
            Colores.imprimirVerde("El votante con dni " + votante.getDni() + " ha entrado en la cola del censo turno (" + getCola().size() + ")");
            votante.setPuedeVotar(true);
        } else {
            Colores.imprimirRojo("El votante con dni " + votante.getDni() + " no está en el censo");
            votante.setPuedeVotar(false);
        }
    }

    /**
     * Método para votar en orden de llegada a la cola y suma uno a la variable numVotos cada vez que se vota
     *
     * @param votante (Hilo de la clase Votante que realiza el voto)
     */
    public synchronized void votar(Votante votante) {
        while (!getCola().get(0).equals(votante.getDni()) || recontando) {
            try {
                Colores.imprimirRojo(votante.getDni() + " ha intentado colarse ( Está recontando = " + recontando + ")");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Colores.imprimirAzul("El votante de dni " + votante.getDni() + " comienza a votar");
            Thread.sleep(tiempoVoto());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("El votante de dni " + votante.getDni() + " acaba de votar");
        numVotos++;
        getCola().remove(0);
        notifyAll();
    }

    /**
     * Método para recontar los votos hasta que el número de votos sea igual al número de personas permitidas para votar
     */
    public void recontarVotos() {
        boolean sigue = true;
        int numVotosAnterior = 0;

        while (sigue) {
            try {
                if (numVotos == ids.size()) {
                    sigue = false;
                } else {
                    Thread.sleep(5000);
                    recontando = true;
                    System.out.println("Los votos de este recuento son --> " + (numVotos - numVotosAnterior));
                    numVotosAnterior = numVotos;
                    Thread.sleep(5000);
                    recontando = false;
                    despertarTodos();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Los recuentos totales de la votación son --> " + numVotos);
    }

    /**
     * Método para despertar todos los hilos dormidos
     */
    private synchronized void despertarTodos() {
        notifyAll();
    }

    /**
     * Método para obtener el tiempo de voto (número random entre 1000 y 3000 milisegundos)
     *
     * @return un número entero que indica el tiempo de voto en milisegundos
     */
    public static int tiempoVoto() {
        return (int) (Math.random() * 3000 + 1000);
    }

    /**
     * Método sincronizado que devuelve la cola de las personas que están en cola para votar en ese momento
     *
     * @return ArrayList que contiene la cola de las personas que están en cola en ese momento
     */
    public synchronized ArrayList<Integer> getCola() {
        return cola;
    }
}
