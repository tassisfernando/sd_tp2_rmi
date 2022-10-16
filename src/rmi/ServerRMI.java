package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRMI extends Remote {
    String welcomeMessage() throws RemoteException;

    String getId() throws RemoteException;

    String connectUser(String id) throws RemoteException;

    String receiveMessage(String id, String message) throws  RemoteException;
}
