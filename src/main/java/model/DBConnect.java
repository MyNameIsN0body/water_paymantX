package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnect {
    //  Database credentials
    private static final String POSTGRES = "org.postgresql.Driver";
    private final String connectionString;
    private final Properties properties;

    public DBConnect(String connectionString, Properties properties){
        this.connectionString = connectionString;
        this.properties = properties;
    }

    public boolean isConnected(){
        try {
            Connection connection = openConnection();
            return connection.isValid(3000);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
    private Connection openConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
            //login DB
            Class.forName(POSTGRES);
            connection = DriverManager.getConnection(connectionString, properties);
            connection.setAutoCommit(false);
        return connection;
    }

    public void insertPerson(String sql) {
        Statement statement = null;
        try {
            Connection connection = openConnection();
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
        Statement statement = null;
        try {
            Connection connection = openConnection();
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
    public void updatePerson(String sql) {
        Statement statement = null;
        try {
            Connection connection = openConnection();
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