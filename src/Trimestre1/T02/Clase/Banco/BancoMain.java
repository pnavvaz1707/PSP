package Trimestre1.T02.Clase.Banco;

public class BancoMain {
    public static void main(String[] args) {
        CuentaCorriente c = new CuentaCorriente(5000);

        Banco b = new Banco(c);

        PersonaRetira persona1 = new PersonaRetira("Persona 1", b);
        PersonaIngresa persona2 = new PersonaIngresa("Persona 2", b);

        persona2.start();
        persona1.start();
    }
}
