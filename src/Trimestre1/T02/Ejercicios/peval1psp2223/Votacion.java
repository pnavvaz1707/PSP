package Trimestre1.T02.Ejercicios.peval1psp2223;

/**
 * PRÁCTICA.............: PEVAL1PSP2223
 * NOMBRE y APELLIDOS...: Pablo Navarro Vázquez
 * CURSO y GRUPO........: 2º Programación de Servicios y Procesos
 * FECHA de ENTREGA.....: 27 / 10 /2022
 */
public class Votacion {
    public static void main(String[] args) {
        ColegioElectoral c = new ColegioElectoral();

        RecuentoVotos r = new RecuentoVotos(c);
        r.start();

        //Bucle que instancia un objeto de la clase Votante y lo inicia 30 veces
        for (int i = 1; i <= 30; i++) {
            Votante votante = new Votante(c, i);
            votante.start();
        }
    }
}
