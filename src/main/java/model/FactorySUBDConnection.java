package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

interface SUBD {
    void initConnection(String host, String  port, String nameDB, String userName, String password);
}

public class FactorySUBDConnection {

    static public SUBD createConnection(String typeOfDriver) {
        switch (typeOfDriver) {
            case "org.postgresql.Driver":
                return new ConnectionPostgres();
            case "com.microsoft.sqlserver.jdbc.SQLServerDriver":
                return new ConnectionMSSQL();
            case "com.mysql.cj.jdbc.Driver":
                return new ConnectionMySQL();
            default:
                return null;
        }
    }
}

class ConnectionPostgres implements SUBD {

    @Override
    public void initConnection(String host, String  port, String nameDB, String userName, String password) {
        System.out.println("connect Postgres");
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://" + host + port + "/" + nameDB;
            Properties props = new Properties();
            props.setProperty("user", userName);
            props.setProperty("password", password);
            props.setProperty("ssl", "true");/// ???
            conn = DriverManager.getConnection(url, props);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}

class ConnectionMSSQL implements SUBD {
    @Override
    public void initConnection(String host, String  port, String nameDB, String userName, String password) {
        System.out.println("connect MSSQL");
        Connection conn = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + host + port + "\\\\" + nameDB;
            Properties props = new Properties();
            props.setProperty("user", userName);
            props.setProperty("password", password);
            props.setProperty("ssl", "true");/// ???
            conn = DriverManager.getConnection(url, props);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}

class ConnectionMySQL implements SUBD {
    @Override
    public void initConnection(String host, String  port, String nameDB, String userName, String password) {
        System.out.println("connect MySQL");
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + host + port + "/" + nameDB;
            Properties props = new Properties();
            props.setProperty("user", userName);
            props.setProperty("password", password);
            props.setProperty("ssl", "true");/// ???
            conn = DriverManager.getConnection(url, props);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}