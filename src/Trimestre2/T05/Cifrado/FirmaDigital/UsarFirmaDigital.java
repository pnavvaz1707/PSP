package Trimestre2.T05.Cifrado.FirmaDigital;

import java.io.File;

public class UsarFirmaDigital {
    public static void main(String[] args) {
        File fClavePrivada = new File("src/Trimestre2/T05/Cifrado/FirmaDigital/ClavePrivada.txt");
        File fClavePublica = new File("src/Trimestre2/T05/Cifrado/FirmaDigital/ClavePublica.txt");
        File ficheroAFirmar = new File("src/Trimestre2/T05/Cifrado/FirmaDigital/FicheroAFirmar.txt");
        File ficheroFirmado = new File("src/Trimestre2/T05/Cifrado/FirmaDigital/FicheroFirmado.txt");
        GeneradorClavesFirmaDigital.generarParDeClavesYGuardarEnFichero(fClavePublica, fClavePrivada);
        FirmadorDigital.firmarDocumento(fClavePrivada,ficheroAFirmar,ficheroFirmado);
        if (FirmadorDigital.comprobarFirma(fClavePublica,ficheroAFirmar,ficheroFirmado)){
            System.out.println("No se ha alterado el documento");
        }else {
            System.out.println("Se ha alterado el documento");
        }
    }
}