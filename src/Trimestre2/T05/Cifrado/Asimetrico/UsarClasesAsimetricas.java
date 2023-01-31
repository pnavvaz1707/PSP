package Trimestre2.T05.Cifrado.Asimetrico;

import java.io.File;

public class UsarClasesAsimetricas {
    public static void main(String[] args) {
        File fClavePrivada = new File("src/Trimestre2/T05/Cifrado/Asimetrico/ClavePrivada.txt");
        File fClavePublica = new File("src/Trimestre2/T05/Cifrado/Asimetrico/ClavePublica.txt");
        File ficheroDescifrado = new File("src/Trimestre2/T05/Cifrado/Asimetrico/Descifrado.txt");
        File ficheroCifrado = new File("src/Trimestre2/T05/Cifrado/Asimetrico/Cifrado.txt");
        GeneradorClavesAsimetricas.generarParDeClavesYGuardarEnFichero(fClavePublica,fClavePrivada);
        CifradorAsimetrico.cifrarFichero(fClavePublica,ficheroDescifrado,ficheroCifrado);
//        DescifradorAsimetrico.descifrarFichero(fClavePrivada,ficheroDescifrado,ficheroCifrado);
    }
}
