package Trimestre2.T05.Cifrado.Simetrico;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class GeneradorClave {
    public static void generarClave(File rutaClave,String algortimo) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algortimo);
            SecretKey key = keyGenerator.generateKey();

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algortimo);

            DESKeySpec keySpec = (DESKeySpec) keyFactory.getKeySpec(key, DESKeySpec.class);

            FileOutputStream os = new FileOutputStream(rutaClave);

            os.write(keySpec.getKey());
            os.close();

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Key recuperarClave(File rutaClave){
        SecretKey key;
        try {
            FileInputStream fisClave = new FileInputStream(rutaClave);

            byte [] clave = new byte[(int) rutaClave.length()];
            fisClave.read(clave);

            fisClave.close();

            DESKeySpec keySpec = new DESKeySpec(clave);

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            key = keyFactory.generateSecret(keySpec);

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return key;
    }
}
