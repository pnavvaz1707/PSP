package Trimestre2.T05.Cifrado.Simetrico;

import java.io.File;

public class UsarClasesSimetricas {
    public static void main(String[] args) {
        File ficheroClave = new File("src/Trimestre2/T05/Cifrado/Simetrico/ClaveP.txt");
        File ficheroCifrado = new File("src/Trimestre2/T05/Cifrado/Simetrico/CifradoP.txt");
        File ficheroDescifrado = new File("src/Trimestre2/T05/Cifrado/Simetrico/DescifradoP.txt");

        GeneradorClavesSimetricas.generarClaveDESYGuardarEnFichero(ficheroClave);
        CifradorSimetrico.cifrarFichero(ficheroClave, ficheroDescifrado, ficheroCifrado);
//        Descifrador.descifrarFichero(ficheroClave,ficheroDescifrado,ficheroCifrado,algoritmo);
    }
}
