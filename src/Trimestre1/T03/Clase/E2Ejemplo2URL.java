package Trimestre1.T03.Clase;

import java.io.*;
import java.net.*;

public class E2Ejemplo2URL {
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
            InputStream inputStream = url.openStream();
            in = new BufferedReader(new InputStreamReader(inputStream));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                if (inputLine.contains("Mbappe") || inputLine.contains("Mbappé")) {
                    contador++;
                }
                System.out.println(inputLine);
            }

            in.close();

            System.out.println("Mbappe aparece " + contador + " veces");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void contarMbappe(String texto, String palabra) {
        if (texto.contains(palabra)) {
            contador++;
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
