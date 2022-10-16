package rmi;

import javax.swing.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import static app.utils.Utils.*;
import static java.util.Objects.nonNull;

public class ClientRMIImpl extends UnicastRemoteObject implements ClientRMI {
    private static final long serialVersionUID = 6471501937415035190L;

    private ServerRMI server;
    String id;
    boolean isChatting = false;

    public ClientRMIImpl() throws RemoteException {
        super();
        initialize();
    }

    private void initialize() {
        try {
            this.server = (ServerRMI) Naming.lookup(SERVER_NAME);
            System.out.println(server.welcomeMessage());
            JOptionPane.showMessageDialog(null, server.welcomeMessage(), "Welcome", JOptionPane.INFORMATION_MESSAGE);

            id = server.getId();
            Naming.rebind(LOCAL_URI+"/"+id, (ClientRMI) this);
            String message = server.connectUser(id);

            if (nonNull(message)) {
                JOptionPane.showMessageDialog(null, message, "Waiting", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new RuntimeException();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o servidor",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void receiveChat() throws RemoteException {
        isChatting = true;
        try {
            String strMessage = JOptionPane.showInputDialog(null, SEND_MESSAGE);
            if (strMessage == null) {
                server.receiveMessage(id, DISCONNECT);
            } else {
                server.receiveMessage(id, strMessage);
                JOptionPane.showMessageDialog(null, WAITING_RESPONSE, "Aguardando", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao enviar mensagem!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        JOptionPane.showMessageDialog(null, message, "Resposta", JOptionPane.INFORMATION_MESSAGE);
        logMessageRecebida(message);

        if (message.trim().equals(END_CHAT)) {
            JOptionPane.showMessageDialog(null, "Até mais!");
            isChatting = false;
        }
    }

    private void logMessageRecebida(String message) {
        System.out.printf("Mensagem recebida: %s\n", message);
    }
}
