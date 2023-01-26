package Trimestre2.T05.Cifrado.Simetrico;

import java.io.File;

public class UsarClase {
    public static void main(String[] args) {
        File ficheroClave = new File("src/Trimestre2/T05/Cifrado/Simetrico/ClaveP.txt");
        File ficheroCifrado = new File("src/Trimestre2/T05/Cifrado/Simetrico/CifradoP.txt");
        File ficheroDescifrado = new File("src/Trimestre2/T05/Cifrado/Simetrico/DescifradoP.txt");

        String algoritmo = "DES";

        GeneradorClave.generarClave(ficheroClave, algoritmo);
        Cifrador.cifrarFichero(ficheroClave, ficheroDescifrado, ficheroCifrado, algoritmo);
//        Descifrador.descifrarFichero(ficheroClave,ficheroDescifrado,ficheroCifrado,algoritmo);
    }
}
