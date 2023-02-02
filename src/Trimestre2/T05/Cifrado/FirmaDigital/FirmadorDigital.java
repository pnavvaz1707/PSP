package Trimestre2.T05.Cifrado.FirmaDigital;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

public class FirmadorDigital {

    public static void firmarDocumento(File fClavePrivada, File ficheroAFirmar, File ficheroFirmado) {
        try {
            Signature signature = Signature.getInstance("DSA");
            signature.initSign(GeneradorClavesFirmaDigital.recuperarClavePrivada(fClavePrivada));
            leerFichero(ficheroAFirmar, signature);
            FileOutputStream outputStream = new FileOutputStream(ficheroFirmado);
            outputStream.write(signature.sign());
            outputStream.close();
        } catch (NoSuchAlgorithmException | IOException | SignatureException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean comprobarFirma(File fClavePublica, File ficheroAFirmar, File ficheroFirmado) {
        try {
            Signature signature = Signature.getInstance("DSA");
            signature.initVerify(GeneradorClavesFirmaDigital.recuperarClavePublica(fClavePublica));

            leerFichero(ficheroAFirmar, signature);
            FileInputStream inputStream;

            inputStream = new FileInputStream(ficheroFirmado);
            byte[] firma = new byte[(int) ficheroFirmado.length()];
            inputStream.read(firma);

            return signature.verify(firma);

        } catch (NoSuchAlgorithmException | IOException | SignatureException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leerFichero(File ficheroAFirmar, Signature signature) throws IOException, SignatureException {
        FileInputStream inputStream = new FileInputStream(ficheroAFirmar);
        byte[] buffer = new byte[64];
        int bytes_leidos = inputStream.read(buffer);
        while (bytes_leidos != -1) {
            signature.update(buffer, 0, bytes_leidos);
            bytes_leidos = inputStream.read(buffer);
        }
        inputStream.close();
    }
}
