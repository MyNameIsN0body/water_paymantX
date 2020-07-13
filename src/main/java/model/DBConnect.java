package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
    //  Database credentials
    private static final String POSTGRES = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "IpMan";

    private DBConnect(){}

    public static DBConnect getDBConnect() {
        return new DBConnect();
    }

    private Connection openConnection() {
        Connection connection = null;
        try {
            //login DB
            Class.forName(POSTGRES);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return connection;
    }

    public void insertPerson(String sql) {
        Connection connection = openConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            System.out.println(sql);
            statement.executeUpdate(sql);
            statement.close();
            connection.commit();
            connection.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void deletePerson(String sql) {
        Connection connection = openConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            System.out.println(sql);
            statement.executeUpdate(sql);
            connection.commit();
            statement.close();
            connection.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public void updatePeople(String sql) {
        Connection connection = openConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
