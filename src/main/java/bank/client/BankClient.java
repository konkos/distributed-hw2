package bank.client;

import bank.server.BankOps;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class BankClient {
    public static void main(String[] args) {
        try {
            BankOps bankOps = (BankOps) Naming.lookup("rmi://localhost/BANKOPS");
            bankOps.deposit(1, 100);
            bankOps.withdraw(2, 20);
            double balance = bankOps.getBalance(3);
            System.out.println("USER 3 has " + balance);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
