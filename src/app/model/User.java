package app.model;

import rmi.ClientRMI;

public class User {

    private String id;
    private ClientRMI clientRMI;

    public User(String id, ClientRMI clientRMI) {
        this.id = id;
        this.clientRMI = clientRMI;
    }

    public String getId() {
        return id;
    }

    public ClientRMI getClientRMI() {
        return clientRMI;
    }
}
