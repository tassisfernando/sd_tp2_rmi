package app;

import rmi.ClientRMI;
import rmi.ClientRMIImpl;

public class ClientMain {

    public static void main(String[] args) {
        ClientMain clientMain = new ClientMain();
        clientMain.startClient();
    }

    private void startClient() {
        try {
            ClientRMI client = new ClientRMIImpl();
        } catch (Exception ex) {
            System.err.println("Erro ao criar usuário (cliente)!");
            System.err.println(ex.getMessage());
        }
    }
}
