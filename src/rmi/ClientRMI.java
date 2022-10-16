package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientRMI extends Remote {

    void receiveChat() throws RemoteException;

    void receiveMessage(String message) throws RemoteException;
}
