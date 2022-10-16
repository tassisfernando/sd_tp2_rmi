package app;

import rmi.ServerRMI;
import rmi.ServerRMIImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import static app.utils.Utils.SERVER_NAME;
import static app.utils.Utils.SERVER_PORT;

public class ServerMain {

    public static void main(String[] args) {
        ServerMain serverMain = new ServerMain();
        serverMain.startServer();
    }

    private void startServer() {
        try {
            LocateRegistry.createRegistry(SERVER_PORT);
            ServerRMI server = new ServerRMIImpl();
            Naming.rebind(SERVER_NAME, server);

            System.out.println("SERVER INICIADO...");
        } catch (Exception ex) {
            System.err.println("Erro ao iniciar o server!");
            System.err.println(ex.getMessage());
        }
    }
}
