package examples.calc;

import java.rmi.Naming;

public class CalculadoraClient {

    public static void main(String[] args) {
        try {
            Calculadora c = (Calculadora) Naming.lookup("rmi://192.168.1.71:1099/CalculadoraService");
            System.out.println("Soma: " + c.add(10, 15));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
