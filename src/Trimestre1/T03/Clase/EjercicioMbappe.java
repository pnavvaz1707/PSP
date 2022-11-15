package Trimestre1.T03.Clase;

import java.io.*;
import java.net.*;

public class EjercicioMbappe {
    static int contador = 0;

    public static void main(String[] args) {
        URL url = null;

        try {
            url = new URL("https://www.as.com");
            visualizar(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in;

        try {
            File f = new File("src/Trimestre1/T03/Clase/FicheroMcGuire.txt");
            FileOutputStream fos = new FileOutputStream(f);

            InputStream inputStream = url.openStream();
            in = new BufferedReader(new InputStreamReader(inputStream));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                contarPalabra(inputLine, "mbappe");
                contarPalabra(inputLine, "Mbappé");

                inputLine = inputLine.replace("mbappe", "McGuire");
                inputLine = inputLine.replace("Mbappé", "McGuire");

                fos.write(inputLine.getBytes());
            }


            fos.close();
            in.close();

            System.out.println("Mbappé aparece " + contador + " veces");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void contarPalabra(String texto, String palabra) {
        if (texto.contains(palabra)) {
            contador++;
            contarPalabra(texto.substring(texto.indexOf(palabra) + 1), palabra);
        }
    }

    private static void visualizar(URL url) {
        System.out.println("\tURL COMPLETA: " + url.toString());
        System.out.println("\tgetProtocol(): " + url.getProtocol());
        System.out.println("\tgetHost(): " + url.getHost());
        System.out.println("\tgetPort(): " + url.getPort());
        System.out.println("\tgetFile(): " + url.getFile());
        System.out.println("\tgetUserInfo(): " + url.getUserInfo());
        System.out.println("\tgetPath(): " + url.getPath());
        System.out.println("\tgetAuthority(): " + url.getAuthority());
        System.out.println("\tgetQuery(): " + url.getQuery());
        System.out.println("===============================================");
    }
}
