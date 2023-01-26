package Trimestre2.T05.Cifrado.Asimetrico;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class Ejemplo {
    public static void main(String[] args) {
        try {
            SecureRandom random = new SecureRandom();

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            keyPairGenerator.initialize(512,random);

            Cipher rsaCipher = Cipher.getInstance("RSA");
            rsaCipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());

            String mensaje = "Mensaje de prueba";
            byte[] mensajeCifrado = rsaCipher.doFinal(mensaje.getBytes());

            rsaCipher.init(Cipher.DECRYPT_MODE, keyPair.getPublic());
            String mensajeDescifrado = new String(rsaCipher.doFinal(mensajeCifrado));

            System.out.println("Mensaje --> " + mensaje);
            System.out.println("Descifrado --> " + mensajeDescifrado);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            throw new RuntimeException(e);
        }

    }
}
