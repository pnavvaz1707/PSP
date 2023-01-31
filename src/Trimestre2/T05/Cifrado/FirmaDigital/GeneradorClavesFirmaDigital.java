package Trimestre2.T05.Cifrado.FirmaDigital;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;

public class GeneradorClavesFirmaDigital {

    public static void generarParDeClavesYGuardarEnFichero(File rutaClavePublica, File rutaClavePrivada) {
        try {
            SecureRandom random = new SecureRandom();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            keyPairGenerator.initialize(512, random);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            KeyFactory keyFactory = KeyFactory.getInstance("DSA");

            DSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey, DSAPrivateKeySpec.class);
            DSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(publicKey, DSAPublicKeySpec.class);

            FileOutputStream os = new FileOutputStream(rutaClavePublica);
            PrintWriter pw = new PrintWriter(os);

            pw.println(publicKeySpec.getY());
            pw.println(publicKeySpec.getP());
            pw.println(publicKeySpec.getQ());
            pw.println(publicKeySpec.getG());
            pw.close();

            os = new FileOutputStream(rutaClavePrivada);
            pw = new PrintWriter(os);

            pw.println(privateKeySpec.getG());
            pw.println(privateKeySpec.getP());
            pw.println(privateKeySpec.getQ());
            pw.println(privateKeySpec.getX());
            pw.close();
        } catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static PrivateKey recuperarClavePrivada(File rutaClavePrivada) {
        PrivateKey privateKey;
        try {
            FileReader fr = new FileReader(rutaClavePrivada);
            BufferedReader br = new BufferedReader(fr);

            BigInteger g = new BigInteger(br.readLine());
            BigInteger p = new BigInteger(br.readLine());
            BigInteger q = new BigInteger(br.readLine());
            BigInteger x = new BigInteger(br.readLine());

            br.close();

            DSAPrivateKeySpec dsaPrivateKeySpec = new DSAPrivateKeySpec(x, p, q, g);

            KeyFactory keyFactory = KeyFactory.getInstance("DSA");
            privateKey = keyFactory.generatePrivate(dsaPrivateKeySpec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return privateKey;
    }

    public static PublicKey recuperarClavePublica(File rutaClavePublica) {
        PublicKey publicKey;
        try {
            FileReader fr = new FileReader(rutaClavePublica);
            BufferedReader br = new BufferedReader(fr);

            BigInteger y = new BigInteger(br.readLine());
            BigInteger p = new BigInteger(br.readLine());
            BigInteger q = new BigInteger(br.readLine());
            BigInteger g = new BigInteger(br.readLine());

            br.close();

            DSAPublicKeySpec dsaPublicKeySpec = new DSAPublicKeySpec(y, p, q, g);

            KeyFactory keyFactory = KeyFactory.getInstance("DSA");
            publicKey = keyFactory.generatePublic(dsaPublicKeySpec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return publicKey;
    }
}
