package bank.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class BankOpsImpl extends UnicastRemoteObject implements BankOps {

    static final String DB_URL = "jdbc:mysql://localhost:3306/test";

    //  Database credentials
    static final String USER = "konkos";
    static final String PASS = "DemoAccount123!@#";

    public BankOpsImpl() throws RemoteException {
    }

    @Override
    public void deposit(int userId, float amount) {
        Connection conn = null;
        Statement stmt = null;
        try {

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            ResultSet resultSet = stmt.executeQuery("SELECT * FROM BANK WHERE userId=" + userId);
            resultSet.next();
            float currentAmount = resultSet.getFloat("amount");
            currentAmount = currentAmount + amount;

            String sql = "UPDATE BANK SET amount= " + currentAmount + " WHERE userId= " + userId;
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    @Override
    public void withdraw(int userId, float amount) {
        Connection conn = null;
        Statement stmt = null;
        try {

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            ResultSet resultSet = stmt.executeQuery("SELECT * FROM BANK WHERE userId=" + userId);
            resultSet.next();
            float currentAmount = resultSet.getFloat("amount");
            currentAmount = currentAmount - amount;

            String sql = "UPDATE BANK SET amount= " + currentAmount + " WHERE userId= " + userId;
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    @Override
    public double getBalance(int userId) {
        double balance = 0;
        Connection conn = null;
        Statement stmt = null;
        try {

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            ResultSet resultSet = stmt.executeQuery("SELECT * FROM BANK WHERE userId=" + userId);
            resultSet.next();
            balance = resultSet.getFloat("amount");

            stmt.close();
            conn.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return balance;
    }
}
