package Trimestre1.T02.Ejercicios.McAuto;


import java.util.ArrayList;

public class Macdonal {
    private ArrayList<Cliente> colaPedir = new ArrayList<>();
    private ArrayList<Cliente> colaRecoger = new ArrayList<>();
    private boolean pedidoListo = false;

    public void ponerseEnCola(Cliente cliente) {
        getColaPedir().add(cliente);
        Colores.imprimirAzul("El " + cliente.nombre + " se ha puesto en cola");
    }

    public synchronized void pedir(Cliente cliente) {
        try {
            while (!getColaPedir().get(0).equals(cliente)) {
                Colores.imprimirRojo("El cliente " + cliente.nombre + " se ha querido colar");
                wait();
            }
            System.out.println("El " + cliente.nombre + " ha empezado a pedir");
            Thread.sleep(tiempoPedido());
            getColaPedir().remove(cliente);
            getColaRecoger().add(cliente);
            Colores.imprimirMorado("El " + cliente.nombre + " ha terminado de pedir");
            notifyAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public synchronized void recoger(Cliente cliente) {
        while (!getColaRecoger().get(0).equals(cliente) || !isPedidoListo()) {
            try {
                Colores.imprimirRojo("El " + cliente.nombre + " se ha intentado colar");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        getColaRecoger().remove(0);
        Colores.imprimirVerde("El " + cliente.nombre + " ya ha recogido el pedido");
        setPedidoListo(false);
//        Colores.imprimirAzul("Cola actual");
        notifyAll();
    }

    public synchronized void cocinar() {
        try {
            while (getColaRecoger().isEmpty()) {
                Colores.imprimirRojo("Está esperando pedidos para cocinar");
                wait();
            }
            Cliente clienteRecoger = getColaRecoger().get(0);
            Colores.imprimirAzul("Cocinando pedido de " + clienteRecoger.nombre + "...");
            Thread.sleep(tiempoCocina());
            Colores.imprimirVerde("Pedido terminado de " + clienteRecoger.nombre);
            setPedidoListo(true);
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized boolean isPedidoListo() {
        return pedidoListo;
    }

    public synchronized void setPedidoListo(boolean pedidoListo) {
        this.pedidoListo = pedidoListo;
    }

    public synchronized ArrayList<Cliente> getColaPedir() {
        return colaPedir;
    }

    public synchronized ArrayList<Cliente> getColaRecoger() {
        return colaRecoger;
    }

    private int tiempoPedido() {
        return (int) ((Math.random() * 5000) + 1000);
    }

    private int tiempoCocina() {
        return (int) ((Math.random() * 2000) + 1000);
    }
}
