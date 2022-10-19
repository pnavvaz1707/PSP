package Trimestre1.T02.Clase.Semaforo;

public class Semaforo {

    String ultimaLuz;
    int tiempo = 10;

    boolean detenido = false;

    public Semaforo() {
        ultimaLuz = "Rojo";
    }

    public synchronized void encenderLuz(Luz hilo) {
        switch (hilo.getName()) {
            case "Verde":
                while (!ultimaLuz.equals("Rojo") && !isDetenido()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                prueba(hilo);
                break;
            case "Amarillo":
                while (!ultimaLuz.equals("Verde") && !isDetenido()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                prueba(hilo);
                break;
            case "Rojo":
                while (!ultimaLuz.equals("Amarillo") && !isDetenido()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                prueba(hilo);
                break;
        }
    }

    public synchronized void apagarLuz(Luz hilo) {
        System.out.println("Se ha apagado la luz " + hilo.getName());
        ultimaLuz = hilo.getName();
        hilo.encendido = false;
        notifyAll();
    }

    public synchronized void restarTiempo() {
        try {
            System.out.println("Restando tiempo (" + tiempo + "s)");
            tiempo = tiempo - 5;
            if (tiempo <= 0) {
                detenido = true;
            }
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void prueba(Luz hilo) {
        if (!isDetenido()) {
            try {
                System.out.println("(" + tiempo + "s) Se ha encendido la luz " + hilo.getName());
                hilo.encendido = true;
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized boolean isDetenido() {
        return detenido;
    }
}
