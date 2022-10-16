package examples.calc;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CalculadoraServer {

    CalculadoraServer() {
        try {
            System.setProperty("java.rmi.rmi.server.hostname", "192.168.1.71");
            LocateRegistry.createRegistry(1099);
            Calculadora c = new CalculadoraImpl();
            Naming.bind("CalculadoraService", c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CalculadoraServer();
    }
}
