package rmi;

import app.model.User;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import static app.utils.Utils.*;

public class ServerRMIImpl extends UnicastRemoteObject implements ServerRMI {

    private static final long serialVersionUID = -5270127808698137880L;

    private final List<User> users;
    private final List<String> ids;
    private boolean endChat = false;

    private User userChatting;
    private User userWaiting;

    public ServerRMIImpl() throws RemoteException {
        super();
        users = new ArrayList<>();
        ids = new ArrayList<>();
    }

    @Override
    public String welcomeMessage() throws RemoteException {
        return WELCOME_MSG;
    }

    @Override
    public String getId() throws RemoteException {
        String id = "User" + ids.size();
        ids.add(id);
        return id;
    }

    @Override
    public String connectUser(String id) throws RemoteException {
        if (users.size() >= MAX_USERS || !ids.contains(id)) {
            return null;
        }

        try {
            ClientRMI client = (ClientRMI) Naming.lookup(LOCAL_URI+"/"+id);
            User user = new User(id, client);
            users.add(user);

            new Thread(() -> {
                if (users.size() == MAX_USERS) {
                    startChat();
                }
            }).start();

            System.out.printf("%s conectado!\n", id);
        } catch (Exception ex) {
            System.out.println("Erro ao conectar usuário!");
            return null;
        }
        return users.size() == MAX_USERS ? WAITING_START : WAITING_USERS;
    }

    private void startChat() {
        try {
            userChatting = users.get(0);
            userWaiting = users.get(1);
            userChatting.getClientRMI().receiveMessage(START_CHAT);
            userChatting.getClientRMI().receiveChat();
        } catch (RemoteException ex) {
            System.err.println("Ocorreu um erro ao iniciar o chat!");
        }
    }

    @Override
    public String receiveMessage(String id, String message) throws RemoteException {
        if (id.equals(userChatting.getId()) && !endChat) {
            if (message.equals(DISCONNECT)) {
                userWaiting.getClientRMI().receiveMessage(END_CHAT);
                logMessageEnviada(userWaiting.getId(), END_CHAT);
                quitChat();
            }

            userWaiting.getClientRMI().receiveMessage(message);
            logMessageEnviada(userWaiting.getId(), message);
            userWaiting.getClientRMI().receiveChat();
        } else {
            return END_CHAT;
        }

        alterUser();
        return message;
    }

    private void quitChat() {
        endChat = true;
        users.clear();
        ids.clear();
        userWaiting = null;
        userChatting = null;
    }

    private void alterUser() {
        User aux = userChatting;
        userChatting = userWaiting;
        userWaiting = aux;
    }

    private void logMessageEnviada(String user, String message) {
        System.out.printf("Mensagem enviada ao user %s: %s\n", user, message);
    }
}
