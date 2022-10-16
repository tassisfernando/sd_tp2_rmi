package examples.calc;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraImpl extends UnicastRemoteObject implements Calculadora {

    private static final long serialVersionUID = 7344140274985509006L;

    public CalculadoraImpl() throws RemoteException {
        super();
    }

    @Override
    public long add(long a, long b) throws RemoteException {
        return a+b;
    }
}
