package Trimestre1.T02;

public class Colores {

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void imprimirRojo(String msg) {
        System.out.println(ANSI_RED + msg + ANSI_RESET);
    }

    public static void imprimirVerde(String msg) {
        System.out.println(ANSI_GREEN + msg + ANSI_RESET);
    }

    public static void imprimirAzul(String msg) {
        System.out.println(ANSI_BLUE + msg + ANSI_RESET);
    }

    public static void imprimirMorado(String msg) {
        System.out.println(ANSI_PURPLE + msg + ANSI_RESET);
    }
}
