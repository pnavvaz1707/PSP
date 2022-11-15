package Trimestre1.T03.Clase.JuegoAdivinanza;

import java.io.IOException;

public class JuegoAdivinanza {

    private int numGenerado;
    boolean adivinado = false;

    public JuegoAdivinanza() {
        numGenerado = (int) (Math.random() * 10);
    }

    public String adivinarNumero(HiloAdivinanza hiloAdivinanza) {
        String mensaje;
        int adivinanza;

        try {
            adivinanza = hiloAdivinanza.entrada.readInt();
            if (adivinanza == numGenerado) {
                mensaje = "El cliente " + hiloAdivinanza.cliente + " ha ganado";
                adivinado = true;
            } else if (adivinanza < numGenerado) {
                mensaje = "El número introducido por el cliente " + hiloAdivinanza.cliente + " es menor";
            } else {
                mensaje = "El número introducido por el cliente " + hiloAdivinanza.cliente + " es mayor";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return mensaje;
    }

    public boolean isAdivinado() {
        return adivinado;
    }
}
