package Trimestre2.T05.Cifrado.Asimetrico;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class GeneradorClavesAsimetricas {
    public static void generarParDeClavesYGuardarEnFichero(File rutaClavePublica, File rutaClavePrivada) {
        try {
            SecureRandom random = new SecureRandom();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512, random);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

//            PrivateKey privateKey = keyPair.getPrivate();
//            PublicKey publicKey = keyPair.getPublic();

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(keyPair.getPrivate(), RSAPrivateKeySpec.class);
            RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(keyPair.getPublic(), RSAPublicKeySpec.class);

            FileOutputStream os = new FileOutputStream(rutaClavePublica);
            PrintWriter pw = new PrintWriter(os);

            pw.println(privateKeySpec.getModulus());
            pw.println(privateKeySpec.getPrivateExponent());
            pw.close();

            FileOutputStream os2 = new FileOutputStream(rutaClavePrivada);
            PrintWriter pw2 = new PrintWriter(os2);

            pw2.println(publicKeySpec.getModulus());
            pw2.println(publicKeySpec.getPublicExponent());
            pw2.close();
        } catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static PrivateKey recuperarClavePrivada(File rutaClavePrivada) {
        PrivateKey privateKey;
        try {
            FileReader fr = new FileReader(rutaClavePrivada);
            BufferedReader br = new BufferedReader(fr);

            BigInteger modulus = new BigInteger(br.readLine());
            BigInteger exponente = new BigInteger(br.readLine());

            br.close();

            RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(modulus, exponente);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(rsaPrivateKeySpec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return privateKey;
    }

    //    public static PublicKey recuperarClavePublica(File rutaClavePublica) {
//        PublicKey publicKey;
//        try {
//            FileReader fr = new FileReader(rutaClavePublica);
//            BufferedReader br = new BufferedReader(fr);
//
//            BigInteger modulus = new BigInteger(br.readLine());
//            BigInteger exponente = new BigInteger(br.readLine());
//
//            br.close();
//
//            RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, exponente);
//
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            publicKey = keyFactory.generatePublic(rsaPublicKeySpec);
//
//        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
//            throw new RuntimeException(e);
//        }
//        return publicKey;
//    }
    public static Key recuperarClavePublica(File rutaClavePublica) {
        Key publicKey;
        try {
            FileReader fr = new FileReader(rutaClavePublica);
            BufferedReader br = new BufferedReader(fr);

            BigInteger modulus = new BigInteger(br.readLine());
            BigInteger exponente = new BigInteger(br.readLine());

            br.close();

            RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, exponente);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(rsaPublicKeySpec);

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return publicKey;
    }
}
