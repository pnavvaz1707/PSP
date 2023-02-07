package Trimestre2.T05.Hash;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FuncionesHash {

    public static String obtenerHashNormal(byte[] info, String algoritmo) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algoritmo);

        md.update(info);

        return new String(md.digest());
    }

    public static String obtenerHashFichero(File f, String algoritmo) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance(algoritmo);

        FileInputStream fis = new FileInputStream(f);

        byte[] buffer = new byte[16];
        int bytes_leidos = fis.read();

        while (bytes_leidos != -1) {
            bytes_leidos = fis.read(buffer);
            md.update(buffer);
        }

        return new String(md.digest());
    }

    public static void guardarHashFichero(String hash, File f) throws IOException {
        PrintWriter pw = new PrintWriter(f);
        pw.write(hash);
        pw.close();
    }

    private static String recuperarHashFichero(File f) throws IOException {
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String hash = br.readLine();
        fr.close();
        br.close();
        return hash;
    }

    public static void main(String[] args) {
        try {

            String cadena = "Emma";
            File fGuardarHash = new File("src/Trimestre2/T05/Hash/HashGuardado.txt");

            FuncionesHash.guardarHashFichero(FuncionesHash.obtenerHashNormal(cadena.getBytes(), "MD5"), fGuardarHash);

            if (FuncionesHash.obtenerHashNormal(cadena.getBytes(), "MD5").equals(FuncionesHash.recuperarHashFichero(fGuardarHash))) {
                System.out.println("No se ha modificado");

            } else {
                System.out.println("Se ha modificado");
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
