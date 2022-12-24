package bank.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankOps extends Remote {

    void deposit(int userId, float amount) throws RemoteException;
    void withdraw(int userId, float amount) throws RemoteException;
    double getBalance(int userId) throws RemoteException;
}
