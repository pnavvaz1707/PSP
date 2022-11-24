package Trimestre1.T03.Ejercicios.SieteYMedio;

public class CartaSieteYMedio {
    private final PaloSieteYMedio palo;
    private final String numero;
    private final double valor;

    public CartaSieteYMedio(PaloSieteYMedio palo, String numero, double valor) {
        this.palo = palo;
        this.numero = numero;
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return numero + " de " + palo.nombre + " (valor = " + valor + ")";
    }

}
