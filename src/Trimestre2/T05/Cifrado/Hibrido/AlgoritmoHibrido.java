package Trimestre2.T05.Cifrado.Hibrido;

import Trimestre2.T05.Cifrado.Asimetrico.CifradorAsimetrico;
import Trimestre2.T05.Cifrado.Asimetrico.DescifradorAsimetrico;
import Trimestre2.T05.Cifrado.Asimetrico.GeneradorClavesAsimetricas;
import Trimestre2.T05.Cifrado.Simetrico.CifradorSimetrico;
import Trimestre2.T05.Cifrado.Simetrico.DescifradorSimetrico;
import Trimestre2.T05.Cifrado.Simetrico.GeneradorClavesSimetricas;

import java.io.File;

public class AlgoritmoHibrido {
    //Algoritmo híbrido (DES Y RSA)
    //Cifro el fichero con una clave simétrica.
    //Para pasársela al otro cifro la clave simétrica con la clave pública del receptor siguiendo el cifrado asimétrico RSA
    //Entonces él descifra con su clave privada asimétrica la clave simétrica encriptada, con la que desencriptará el archivo
    public static void main(String[] args) {
        File fOriginal = new File("src/Trimestre2/T05/Cifrado/Hibrido/FicheroOriginal.txt");

        File fClaveDES = new File("src/Trimestre2/T05/Cifrado/Hibrido/ClaveDES.txt");
        File fCifrado = new File("src/Trimestre2/T05/Cifrado/Hibrido/FicheroCifrado.txt");

        File fClaveDESCifrada = new File("src/Trimestre2/T05/Cifrado/Hibrido/ClaveDESCifrada.txt");

        File fClavePublicaRSA = new File("src/Trimestre2/T05/Cifrado/Hibrido/ClavePublicaRSA.txt");
        File fClavePrivadaRSA = new File("src/Trimestre2/T05/Cifrado/Hibrido/ClavePrivadaRSA.txt");

        File fClaveDESDescifrada = new File("src/Trimestre2/T05/Cifrado/Hibrido/ClaveDESDescifrada.txt");
        File fOriginalDescifrado = new File("src/Trimestre2/T05/Cifrado/Hibrido/FicheroOriginalDescifrado.txt");

        //Generamos las claves
        GeneradorClavesSimetricas.generarClaveDESYGuardarEnFichero(fClaveDES);
        GeneradorClavesAsimetricas.generarParDeClavesYGuardarEnFichero(fClavePublicaRSA, fClavePrivadaRSA);

        //Ciframos el texto
        CifradorSimetrico.cifrarFichero(fClaveDES, fOriginal, fCifrado);
        //Ciframos la llave simétrica con la clave pública del receptor
        CifradorAsimetrico.cifrarFichero(fClavePublicaRSA, fClaveDES, fClaveDESCifrada);

        //Desciframos con la clave privada del receptor el fichero que contiene la clave simétrica cifrada
        DescifradorAsimetrico.descifrarFichero(fClavePrivadaRSA, fClaveDESDescifrada, fClaveDESCifrada);
        //Desciframos el fichero cifrado con la clave simétrica que hemos descifrado antes
        DescifradorSimetrico.descifrarFichero(fClaveDESDescifrada, fOriginalDescifrado, fCifrado);
    }
}
