package Trimestre1.T03.Clase;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class E1TestInetAdress {
    public static void main(String[] args) {
        InetAddress dir = null;
        try {
            System.out.println("======================");
            System.out.println("SALIDA PARA LOCALHOST: ");
            dir = InetAddress.getByName("localhost");
            pruebaMetodos(dir);


            System.out.println("======================");
            System.out.println("SALIDA PARA UNA URL: ");
            dir = InetAddress.getByName("www.twitter.com");
            pruebaMetodos(dir);

            System.out.println("======================");
            System.out.println("DIRECCIONES IP PARA: " + dir.getHostName());
            InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
            for (int i = 0; i < direcciones.length; i++) {
                System.out.println(direcciones[i].toString());
            }
            System.out.println("======================");
            pruebaMetodos(dir);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

    }

    private static void pruebaMetodos(InetAddress dir) {
        System.out.println("Método getByName(): " + dir);
        InetAddress dir2;
        try {
            dir2 = InetAddress.getLocalHost();
            System.out.println("Método getLocalHost(): " + dir2);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Méotod getHostName(): " + dir.getHostName());
        System.out.println("Méotod getHostAddress(): " + dir.getHostAddress());
        System.out.println("Méotod toString(): " + dir.toString());
        System.out.println("Méotod getCanonicalHostName(): " + dir.getCanonicalHostName());
    }
}
