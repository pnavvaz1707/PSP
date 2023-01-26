package Trimestre2.T05.Cifrado.Simetrico;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Descifrador {
    public static void descifrarFichero(File clave, File rutaFicheroDescifrado, File ficheroCifrado,String algoritmo){
        try {
            Cipher cipher = Cipher.getInstance(algoritmo);
            cipher.init(Cipher.DECRYPT_MODE, GeneradorClave.recuperarClave(clave));

            FileInputStream fis = new FileInputStream(ficheroCifrado);
            FileOutputStream fos = new FileOutputStream(rutaFicheroDescifrado);

            byte [] buffer = new byte[16];
            int bytes_leidos = fis.read(buffer);

            while (bytes_leidos != -1){
                fos.write(cipher.doFinal(buffer,0,bytes_leidos));
                bytes_leidos = fis.read(buffer);
            }

            fos.close();
            fis.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IOException |
                 IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
