package Trimestre1.T03.Ejercicios.JuegoAdivinanza;

import java.io.IOException;

public class JuegoAdivinanza {

    private int numGenerado;
    boolean adivinado = false;

    public JuegoAdivinanza() {
        numGenerado = (int) (Math.random() * 10);
    }

    public String adivinarNumero(HiloAdivinanza hiloAdivinanza) throws IOException {
        String mensaje;
        int adivinanza;

        System.out.println("El cliente va a adivinar");

        hiloAdivinanza.salida.writeUTF("Introduce un número");
        adivinanza = hiloAdivinanza.entrada.readInt();
        System.out.println("Ha elegido " + adivinanza);
        if (adivinanza == numGenerado) {
            mensaje = "El cliente " + hiloAdivinanza.cliente + " ha ganado";
            adivinado = true;
        } else if (adivinanza < numGenerado) {
            mensaje = "El número introducido por el cliente " + hiloAdivinanza.cliente + " es menor";
        } else {
            mensaje = "El número introducido por el cliente " + hiloAdivinanza.cliente + " es mayor";
        }
        return mensaje;
    }

    public boolean isAdivinado() {
        return adivinado;
    }
}
