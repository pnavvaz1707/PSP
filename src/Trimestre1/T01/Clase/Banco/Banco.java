package Trimestre1.T01.Clase.Banco;

public class Banco {
    CuentaCorriente cuentaCorriente;

    public Banco(CuentaCorriente c) {
        this.cuentaCorriente = c;
    }


    public void ingresar(String nombre, int i) {
        synchronized (cuentaCorriente) {
            this.cuentaCorriente.setSaldo(cuentaCorriente.getSaldo() + i);
            System.out.println("Tras ingresar la persona " + nombre + " (" + i + ") quedan " + this.cuentaCorriente.getSaldo());
            cuentaCorriente.notifyAll();
        }
    }

    public void retirar(String nombre, int i) {
        synchronized (cuentaCorriente) {
            while (cuentaCorriente.getSaldo() <= 4000) {
                try {
                    System.out.println("Cuenta bloqueada por poco dinero");
                    cuentaCorriente.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.cuentaCorriente.setSaldo(cuentaCorriente.getSaldo() - i);
            System.out.println("Tras retirar la persona " + nombre + " (" + i + ") quedan " + this.cuentaCorriente.getSaldo());
        }
    }


}
