package Trimestre2.T05.Cifrado.FirmaDigital;

import java.io.File;
import java.security.*;

public class UsarFirmaDigital {
    public static void main(String[] args) {
        try {
            File fClavePrivada = new File("src/Trimestre2/T05/Cifrado/FirmaDigital/ClavePrivada.txt");
            File fClavePublica = new File("src/Trimestre2/T05/Cifrado/FirmaDigital/ClavePublica.txt");
            File ficheroDescifrado = new File("src/Trimestre2/T05/Cifrado/FirmaDigital/Descifrado.txt");
            File ficheroCifrado = new File("src/Trimestre2/T05/Cifrado/FirmaDigital/Cifrado.txt");
            GeneradorClavesFirmaDigital.generarParDeClavesYGuardarEnFichero(fClavePublica, fClavePrivada);

            Signature signature = Signature.getInstance("DSA");
            signature.initSign(GeneradorClavesFirmaDigital.recuperarClavePrivada(fClavePrivada));
            String mensaje = "Mesaje pafirma";
            signature.update(mensaje.getBytes());
            byte[] firma = signature.sign();
            signature.initVerify(GeneradorClavesFirmaDigital.recuperarClavePublica(fClavePublica));
            signature.update(mensaje.getBytes());
            if (signature.verify(firma)) {
                System.out.println("Ta bn");
            }
        } catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
