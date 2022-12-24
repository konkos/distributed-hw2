package main;

import java.sql.*;

public class Main {
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";

    //  Database credentials
    static final String USER = "konkos";
    static final String PASS = "DemoAccount123!@#";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();
            insertData(stmt);

            fetchAllData(stmt);

            // STEP 4: Clean-up environment
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

    private static void fetchAllData(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM BANK");
        int i = 1;
        while (rs.next()) {
            int id = rs.getInt(1);
            System.out.println("ID " + id);
            i++;
        }
    }

    private static void insertData(Statement stmt) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS BANK " +
                "(id INTEGER not NULL, " +
                " userId INTEGER, " +
                " amount FLOAT, " +
                " PRIMARY KEY ( id ))";
        stmt.executeUpdate(sql);
        System.out.println("Created table in given database...");

        if (stmt.getMaxRows() != 0) return;
        System.out.println("Inserting data to DB");
        stmt.execute("INSERT INTO BANK (id,amount,userId) VALUES (1,100,1)");
        stmt.execute("INSERT INTO BANK (id,amount,userId) VALUES (2,230,2)");
        stmt.execute("INSERT INTO BANK (id,amount,userId) VALUES (3,65,3)");
        System.out.println("DONE Inserting data to DB");
    }

}

