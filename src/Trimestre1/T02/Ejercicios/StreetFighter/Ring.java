package Trimestre1.T02.Ejercicios.StreetFighter;

import Trimestre1.T02.Colores;

import java.util.ArrayList;

public class Ring {

    ArrayList<Luchador> luchadores = new ArrayList<>();
    boolean terminado = false;

    public synchronized void defender(Luchador l) {
        while (l.isTurno()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!terminado) {
            try {
                l.setAccionElegida("Defender");

                if (l.getName().equals("Luchador 1")) {
                    Colores.imprimirMorado("El " + l.getName() + " ha elegido " + l.getAccionElegida());
                } else {
                    Colores.imprimirVerde("El " + l.getName() + " ha elegido " + l.getAccionElegida());
                }

                l.setTurno(true);

                notifyAll();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void cargarPoder(Luchador l) {
        while (l.isTurno()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!terminado) {
            try {
                l.setAccionElegida("CargarPoder");

                if (l.getName().equals("Luchador 1")) {
                    Colores.imprimirMorado("El " + l.getName() + " ha elegido " + l.getAccionElegida());
                } else {
                    Colores.imprimirVerde("El " + l.getName() + " ha elegido " + l.getAccionElegida());
                }

                l.setPoderCargado(true);

                l.setTurno(true);

                notifyAll();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void golpear(Luchador l) {
        while (l.isTurno()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!terminado) {
            try {
                if (l.isPoderCargado()) {
                    l.setAccionElegida("Golpear");

                    if (l.getName().equals("Luchador 1")) {
                        Colores.imprimirMorado("El " + l.getName() + " ha elegido " + l.getAccionElegida());
                    } else {
                        Colores.imprimirVerde("El " + l.getName() + " ha elegido " + l.getAccionElegida());
                    }

                    l.setPoderCargado(false);

                    l.setTurno(true);

                    notifyAll();
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void decidirTurno() {
        while (!comprobarTurno()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Luchador l1 = this.luchadores.get(0);
        Luchador l2 = this.luchadores.get(1);

        switch (l2.getAccionElegida()) {
            case "Defender":
                switch (l1.getAccionElegida()) {
                    case "Golpear":
                        l2.restarVida(1);
                        break;
                }
                break;
            case "CargarPoder":
                switch (l1.getAccionElegida()) {
                    case "Golpear":
                        l2.restarVida(5);
                        break;
                }
                break;
            case "Golpear":
                switch (l1.getAccionElegida()) {
                    case "Golpear":
                        l2.restarVida(3);
                        l1.restarVida(3);
                        break;
                    case "CargarPoder":
                        l1.restarVida(5);
                        break;
                    case "Defender":
                        l1.restarVida(1);
                        break;
                }
                break;
        }

        if (l1.getVida() <= 0 || l2.getVida() <= 0) {
            terminado = true;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        l1.setTurno(false);
        l2.setTurno(false);

        Colores.imprimirMorado("Vida restante " + l1.getName() + ": " + l1.getVida());
        Colores.imprimirVerde("Vida restante " + l2.getName() + ": " + l2.getVida());

        Colores.imprimirRojo("////////////////////////////////////////////////////////////////////////////////");

        if (terminado) {

            if (l1.getVida() > l2.getVida()) {
                Colores.imprimirAzul("El ganador es " + l1.getName());
            }
            else if (l2.getVida() > l1.getVida()) {
                Colores.imprimirAzul("El ganador es " + l2.getName());
            }
            else {
                Colores.imprimirAzul("Ha sido empate");
            }
        }

        notifyAll();
    }

    public void addLuchador(Luchador luchador) {
        this.luchadores.add(luchador);
    }

    private boolean comprobarTurno() {
        boolean finalizado = true;
        for (Luchador luchador : this.luchadores) {
            if (!luchador.isTurno()) {
                finalizado = false;
            }
        }
        return finalizado;
    }

    public synchronized boolean isTerminado() {
        return terminado;
    }
}
