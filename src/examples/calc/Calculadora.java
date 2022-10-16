package examples.calc;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculadora extends Remote {

    long add(long a, long b) throws RemoteException;
}
